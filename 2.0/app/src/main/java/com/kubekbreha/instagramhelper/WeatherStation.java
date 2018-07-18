package com.kubekbreha.instagramhelper;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yarolegovich on 08.03.2017.
 */

public class WeatherStation {


    public static WeatherStation get() {
        return new WeatherStation();
    }

    private WeatherStation() {
    }

    public List<Forecast> getForecasts() {
        return Arrays.asList(
                new Forecast("Pisa",  "16", Weather.PARTLY_CLOUDY),
                new Forecast("Paris", "14", Weather.CLEAR),
                new Forecast("New York",  "9", Weather.MOSTLY_CLOUDY),
                new Forecast("Rome",  "18", Weather.PARTLY_CLOUDY),
                new Forecast("London",  "6", Weather.PERIODIC_CLOUDS),
                new Forecast("Washington", "20", Weather.CLEAR));
    }
}
