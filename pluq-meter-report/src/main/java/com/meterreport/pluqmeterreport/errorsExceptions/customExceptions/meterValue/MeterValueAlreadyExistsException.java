package com.meterreport.pluqmeterreport.errorsExceptions.customExceptions.meterValue;

public class MeterValueAlreadyExistsException extends RuntimeException {
    public MeterValueAlreadyExistsException(String message) {
        super(message);
    }
}
