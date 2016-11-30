package com.victorherraiz.pocs;


import com.w3schools.xml.CelsiusToFahrenheit;
import com.w3schools.xml.CelsiusToFahrenheitResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.transport.http.ClientHttpRequestMessageSender;


public class TempConverterClientImpl extends WebServiceGatewaySupport implements TempConverterClient {

    private static final Logger log = LoggerFactory.getLogger(TempConverterClientImpl.class);
    private static final int CONNECTION_TIMEOUT = -1;
    private static final int READ_TIMEOUT = -1;

    public TempConverterClientImpl() {
        final SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(CONNECTION_TIMEOUT);
        requestFactory.setReadTimeout(READ_TIMEOUT);
        setMessageSender(new ClientHttpRequestMessageSender(requestFactory));
    }

    @Override
    public CelsiusToFahrenheitResponse celsiusToFahrenheit(String celsius) {

        CelsiusToFahrenheit request = new CelsiusToFahrenheit();
        request.setCelsius(celsius);

        log.debug("Requesting conversion to fahrenheit for {} celsius", celsius);

        return (CelsiusToFahrenheitResponse) getWebServiceTemplate()
                .marshalSendAndReceive(
                        request,
                        new SoapActionCallback("http://www.w3schools.com/xml/CelsiusToFahrenheit"));

    }

}