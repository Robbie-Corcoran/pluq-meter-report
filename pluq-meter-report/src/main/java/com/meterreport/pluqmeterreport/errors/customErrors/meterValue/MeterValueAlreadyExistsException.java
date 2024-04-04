package com.meterreport.pluqmeterreport.errors.customErrors.meterValue;

public class MeterValueAlreadyExistsException extends RuntimeException {
    public MeterValueAlreadyExistsException(String message) {
        super(message);
    }
}
