package com.example.thomas.trailcaden;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yvann on 10/02/18.
 */

public class ApiWeather extends AsyncTask <Void,TextView, String> {
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
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        Log.i("INFO", response);

    }


}

