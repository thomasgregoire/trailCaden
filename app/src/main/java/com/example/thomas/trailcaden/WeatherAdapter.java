package com.example.thomas.trailcaden;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yvann on 12/02/18.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {

    private List<WeatherObject> weatherList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView temp, description, date , heure, speed;

        public MyViewHolder(View view) {
            super(view);
            temp = view.findViewById(R.id.temp);
            description = view.findViewById(R.id.description);
            date = view.findViewById(R.id.date);
            heure = view.findViewById(R.id.heure);
            speed = view.findViewById(R.id.speed);
        }
    }

    public WeatherAdapter (List<WeatherObject> list){
        this.weatherList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_list_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        WeatherObject weatherObject = this.weatherList.get(position);
        holder.temp.setText(Double.toString(weatherObject.getTemp()) + " °C");
        holder.heure.setText(weatherObject.getHeure());
        holder.date.setText(weatherObject.getDateJ());
        holder.description.setText(weatherObject.getDescription());
        holder.speed.setText(Double.toString(weatherObject.getSpeed())+" km/h");
    }

    @Override
    public int getItemCount() {
        return this.weatherList.size();
    }
}
