package com.victorherraiz.pocs;


public class TemperatureConverterService {

    private final TempConverterClient tempConverterClient;

    public TemperatureConverterService(TempConverterClient tempConverterClient) {
        this.tempConverterClient = tempConverterClient;
    }

    Temperature celsiusToFahrenheit(String celsius) {
        return new Temperature(this.tempConverterClient.celsiusToFahrenheit(celsius));
    }

}
