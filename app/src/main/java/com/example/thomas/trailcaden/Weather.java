package com.example.thomas.trailcaden;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Weather extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        new ApiWeather().execute();

    }

    public class ApiWeather extends AsyncTask<Void,TextView, String> {
        private final String urlImageWeather = "http://openweathermap.org/img/w/";
        private final String pngImageWeather = ".png";
        private final String urlForecastWeather = "api.openweathermap.org/data/2.5/forecast?";
        private final String idCadenWeather = "3029257";
        private final String apiKeyWeather = "3989eaa12bbbbd9c104074849f3ad937";
        private Exception exception;



        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?lat=47.63666&lon=-2.28&units=metric&lang=fr&APPID=3989eaa12bbbbd9c104074849f3ad937");
                // URL url = new URL("http://openweathermap.org/img/w/04n.png");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }



        protected void onPostExecute(String response) {
            List<WeatherObject> weatherList = new ArrayList<>();
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            try {
                JSONObject json = new JSONObject(response);
                JSONArray jsonArray = new JSONArray(json.getJSONArray("list").toString());
                for(int i = 0 ; i< jsonArray.length(); i++){
                    JSONObject list = new JSONObject(jsonArray.get(i).toString());
                    JSONObject weather = new JSONObject(list.getJSONArray("weather").get(0).toString());
                    JSONObject main = new JSONObject(list.getJSONObject("main").toString());
                    JSONObject wind = new JSONObject(list.getJSONObject("wind").toString());
                    double temp = main.getDouble("temp");
                    double humidity = main.getDouble("humidity");
                    double speed = wind.getDouble("speed");
                    String description =  weather.getString("description");
                    String icon = weather.getString("icon");
                    long dt = list.getLong("dt");
                    Date date = new Date(dt*1000L);
                    SimpleDateFormat heure = null;
                    SimpleDateFormat jour = null;
                    jour = new SimpleDateFormat("d MMMM Y", Locale.FRANCE);
                    heure = new SimpleDateFormat("H:mm", Locale.FRANCE);
                    System.out.println(heure.format(date));
                    System.out.println(jour.format(date));

                    WeatherObject w = new WeatherObject(temp,humidity,speed,description,icon,dt,jour.format(date),heure.format(date));


                    weatherList.add(w);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            TextView fi = findViewById(R.id.textView);
            fi.setText(response);
            Log.i("INFO", weatherList.toString());

        }


    }
}
