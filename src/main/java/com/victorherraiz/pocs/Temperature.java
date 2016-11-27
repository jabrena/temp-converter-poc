package com.victorherraiz.pocs;


import com.w3schools.tempconvert.CelsiusToFahrenheitResponse;
import com.w3schools.tempconvert.FahrenheitToCelsiusResponse;

import java.math.BigDecimal;

public class Temperature {

    private final BigDecimal value;
    private final Unit unit;

    public Temperature(final CelsiusToFahrenheitResponse response) {
        this.value = new BigDecimal(response.getCelsiusToFahrenheitResult());
        this.unit = Unit.Fahrenheit;
    }


    public Temperature(final FahrenheitToCelsiusResponse response) {
        this.value = new BigDecimal(response.getFahrenheitToCelsiusResult());
        this.unit = Unit.Celsius;
    }

    public Unit getUnit() {
        return unit;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "value=" + value +
                ", unit=" + unit +
                '}';
    }

    public enum Unit {Fahrenheit, Celsius}
}
