package com.victorherraiz.pocs;

import com.w3schools.xml.CelsiusToFahrenheitResponse;

/**
 * Created by C0228983 on 28/11/2016.
 */
public interface TempConverterClient {
    CelsiusToFahrenheitResponse celsiusToFahrenheit(String celsius);
}
