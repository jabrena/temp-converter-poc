package com.victorherraiz.pocs;

import com.w3schools.xml.CelsiusToFahrenheitResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


@Configuration
public class TemperatureConverterConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.w3schools.xml");
        return marshaller;
    }

    @Bean
    @Profile("!stub")
    public TempConverterClient tempConverterClient(Jaxb2Marshaller marshaller) {
        TempConverterClientImpl client = new TempConverterClientImpl();
        client.setDefaultUri("http://www.w3schools.com/xml/tempconvert.asmx");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public TemperatureConverterService temperatureConverterService(TempConverterClient tempConverterClient) {
        return new TemperatureConverterServiceImpl(tempConverterClient);
    }

    @Bean
    @ConditionalOnMissingBean(TempConverterClient.class)
    public TempConverterClient tempConverterClientMock () {
        return new TempConverterClient() {
            @Override
            public CelsiusToFahrenheitResponse celsiusToFahrenheit(String celsius) {
                CelsiusToFahrenheitResponse response = new CelsiusToFahrenheitResponse();
                response.setCelsiusToFahrenheitResult("666.66");
                return response;
            }
        };
    }

}