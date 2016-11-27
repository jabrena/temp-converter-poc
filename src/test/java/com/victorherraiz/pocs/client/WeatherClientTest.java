package com.victorherraiz.pocs.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victorherraiz.pocs.WeatherConfiguration;
import com.w3schools.tempconvert.CelsiusToFahrenheitResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.test.client.MockWebServiceServer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.ws.test.client.RequestMatchers.soapEnvelope;
import static org.springframework.ws.test.client.ResponseCreators.withSoapEnvelope;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeatherConfiguration.class)
public class WeatherClientTest {

    private static final Logger log = LoggerFactory.getLogger(WeatherClientTest.class);

    @Autowired
    private TempConverterClient client;

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
    public void getCityForecastByZip() throws Exception {
        mockServer.expect(soapEnvelope(mockrequest)).andRespond(withSoapEnvelope(response));
        final CelsiusToFahrenheitResponse response = client.celsiusToFahrenheit("34");
        assertThat(response, hasProperty("celsiusToFahrenheitResult", is("93.2")));
        log.info(new ObjectMapper().writeValueAsString(response));

        mockServer.verify();

    }

}