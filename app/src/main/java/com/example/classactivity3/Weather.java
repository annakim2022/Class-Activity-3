package com.example.classactivity3;

public class Weather {

    private String dt;
    private String weather;
    private String main;

    public Weather (String dt, String weather, String main) {
        this.dt = dt;
        this.weather = weather;
        this.main = main;
    }


    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }
}
