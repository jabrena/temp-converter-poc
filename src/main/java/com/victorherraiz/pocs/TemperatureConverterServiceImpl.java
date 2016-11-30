package com.victorherraiz.pocs;


public class TemperatureConverterServiceImpl implements TemperatureConverterService {

    private final TempConverterClient tempConverterClient;

    public TemperatureConverterServiceImpl(TempConverterClient tempConverterClient) {
        this.tempConverterClient = tempConverterClient;
    }

    public Temperature celsiusToFahrenheit(String celsius) {
        return new Temperature(this.tempConverterClient.celsiusToFahrenheit(celsius));
    }

}
