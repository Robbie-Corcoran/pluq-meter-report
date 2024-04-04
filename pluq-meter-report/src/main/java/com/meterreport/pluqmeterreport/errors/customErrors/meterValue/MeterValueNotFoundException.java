package com.meterreport.pluqmeterreport.errors.customErrors.meterValue;

public class MeterValueNotFoundException extends RuntimeException {
    public MeterValueNotFoundException(String message) {
        super(message);
    }
}
