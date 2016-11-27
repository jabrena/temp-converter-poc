package com.victorherraiz.pocs;

import com.victorherraiz.pocs.client.TempConverterClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


@Configuration
public class WeatherConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.w3schools.tempconvert");
        return marshaller;
    }

    @Bean
    public TempConverterClient weatherClient(Jaxb2Marshaller marshaller) {
        TempConverterClient client = new TempConverterClient();
        client.setDefaultUri("http://www.w3schools.com/xml/tempconvert.asmx");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public WeatherService weatherService(TempConverterClient tempConverterClient) {
        return new WeatherService(tempConverterClient);
    }

}