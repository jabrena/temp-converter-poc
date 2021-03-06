package com.victorherraiz.pocs;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.test.client.MockWebServiceServer;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.ws.test.client.RequestMatchers.soapEnvelope;
import static org.springframework.ws.test.client.ResponseCreators.withSoapEnvelope;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TemperatureConverterConfiguration.class)
public class TemperatureConverterServiceIntegrationTest {

    @Autowired
    private TempConverterClientImpl client;

    @Autowired
    private TemperatureConverterServiceImpl service;

    @Value("classpath:/request.xml")
    private Resource mockrequest;

    @Value("classpath:/response.xml")
    private Resource response;

    private MockWebServiceServer mockServer;

    @Before
    public void createServer() throws Exception {
        mockServer = MockWebServiceServer.createServer(client);
    }

    @Test
    public void celsiusToFahrenheit() throws Exception {
        mockServer.expect(soapEnvelope(mockrequest)).andRespond(withSoapEnvelope(response));
        final Temperature forecast = service.celsiusToFahrenheit("34");
        assertThat(forecast, hasProperty("value", is(new BigDecimal("93.2"))));
        assertThat(forecast, hasProperty("unit", is(Temperature.Unit.Fahrenheit)));
        mockServer.verify();
    }

}