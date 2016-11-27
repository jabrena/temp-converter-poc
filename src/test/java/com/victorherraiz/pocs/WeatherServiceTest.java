package com.victorherraiz.pocs;

import com.victorherraiz.pocs.client.TempConverterClient;
import com.w3schools.tempconvert.CelsiusToFahrenheitResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WeatherConfiguration.class)
public class WeatherServiceTest {

    @MockBean
    private TempConverterClient client;

    @Autowired
    private WeatherService service;

    @Before
    public void before() {
        reset(client);
    }

    @Test
    public void testGetZip() throws Exception {
        final CelsiusToFahrenheitResponse response = new CelsiusToFahrenheitResponse();
        response.setCelsiusToFahrenheitResult("93.2");

        given(client.celsiusToFahrenheit("34")).willReturn(response);

        final Temperature forecast = service.celsiusToFahrenheit("34");
        assertThat(forecast, hasProperty("value", is(new BigDecimal("93.2"))));
        assertThat(forecast, hasProperty("unit", is(Temperature.Unit.Fahrenheit)));

        verify(client, times(1)).celsiusToFahrenheit("34");

    }

}