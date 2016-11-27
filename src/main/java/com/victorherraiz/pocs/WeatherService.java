package com.victorherraiz.pocs;


import com.victorherraiz.pocs.client.TempConverterClient;

public class WeatherService {

    private final TempConverterClient tempConverterClient;

    public WeatherService(TempConverterClient tempConverterClient) {
        this.tempConverterClient = tempConverterClient;
    }

    Temperature celsiusToFahrenheit(String celsius) {
        return new Temperature(this.tempConverterClient.celsiusToFahrenheit(celsius));
    }

}
