package com.meterreport.pluqmeterreport.errorsExceptions.customExceptions.location;

public class LocationAlreadyExistsException extends RuntimeException {
    public LocationAlreadyExistsException(String message) {
        super(message);
    }
}