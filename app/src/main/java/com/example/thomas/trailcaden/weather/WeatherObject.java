package com.example.thomas.trailcaden.weather;

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
                ", dateJ='" + dateJ + '\'' +
                ", heure='" + heure + '\'' +
                '}';
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public String getDateJ() {
        return dateJ;
    }

    public void setDateJ(String dateJ) {
        this.dateJ = dateJ;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
}
