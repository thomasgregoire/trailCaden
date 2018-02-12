package com.example.thomas.trailcaden;

/**
 * Created by yvann on 10/02/18.
 */

public class WeatherObject {
    public double temp;
    public double humidity;
    public double speed;
    public String description;
    public String icon;
    public long dt;
    public String dateJ;
    public String heure;

    public WeatherObject(double temp, double humidity, double speed, String description, String icon, long dt, String dateJ, String heure) {
        this.temp = temp;
        this.humidity = humidity;
        this.speed = speed;
        this.description = description;
        this.icon = icon;
        this.dt = dt;
        this.dateJ = dateJ;
        this.heure = heure;
    }

    @Override
    public String toString() {
        return "WeatherObject{" +
                "temp=" + temp +
                ", humidity=" + humidity +
                ", speed=" + speed +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", dt=" + dt +
                '}';
    }
}
