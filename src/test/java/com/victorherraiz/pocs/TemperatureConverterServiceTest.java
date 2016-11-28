package com.victorherraiz.pocs;

import com.w3schools.xml.CelsiusToFahrenheitResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TemperatureConverterConfiguration.class)
public class TemperatureConverterServiceTest {

    @MockBean
    private TempConverterClient client;

    @Autowired
    private TemperatureConverterService service;

    @Before
    public void before() {
        reset(client);
    }

    @Test
    public void celsiusToFahrenheit() throws Exception {

        final CelsiusToFahrenheitResponse response = new CelsiusToFahrenheitResponse();
        response.setCelsiusToFahrenheitResult("93.2");

        given(client.celsiusToFahrenheit("34")).willReturn(response);

        final Temperature temperature = service.celsiusToFahrenheit("34");
        assertThat(temperature, hasProperty("value", is(new BigDecimal("93.2"))));
        assertThat(temperature, hasProperty("unit", is(Temperature.Unit.Fahrenheit)));

        then(client).should(times(1)).celsiusToFahrenheit("34");

    }

}