package com.kubekbreha.instagramhelper;

/**
 * Created by yarolegovich on 08.03.2017.
 */

public class Forecast {

    private final String cityName;
    private final String temperature;
    private final Weather weather;

    public Forecast(String cityName,  String temperature, Weather weather) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.weather = weather;
    }

    public String getCityName() {
        return cityName;
    }

    public String getTemperature() {
        return temperature;
    }

    public Weather getWeather() {
        return weather;
    }
}
