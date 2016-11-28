package com.victorherraiz.pocs;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebServiceClientPOC {

    public static void main(String[] args) {
        SpringApplication.run(WebServiceClientPOC.class, args);
    }

    @Bean
    CommandLineRunner lookup(TemperatureConverterService service) {
        return args -> {
            String celsius = "34";

            if (args.length > 0) {
                celsius = args[0];
            }
            final Temperature response = service.celsiusToFahrenheit(celsius);
            System.out.println(response);
        };
    }


}
