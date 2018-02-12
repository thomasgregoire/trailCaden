package com.example.thomas.trailcaden;

/**
 * Created by yvann on 10/02/18.
 */

public class WeatherObject {
    public String temperature;
    public String weather_desc;
    public String img;
    public String hour;
    public String date;


    public WeatherObject(String temperature, String weather_desc, String img, String hour, String date) {
        this.temperature = temperature;
        this.weather_desc = weather_desc;
        this.img = img;
        this.hour = hour;
        this.date = date;
    }
}
