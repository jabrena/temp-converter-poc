package com.victorherraiz.pocs;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private final TemperatureConverterService service;

    public Application(TemperatureConverterService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        final Temperature response = service.celsiusToFahrenheit("34");
        System.out.println(response);
    }
}
