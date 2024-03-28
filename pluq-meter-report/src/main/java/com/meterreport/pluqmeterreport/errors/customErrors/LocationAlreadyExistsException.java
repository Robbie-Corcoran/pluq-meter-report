package com.meterreport.pluqmeterreport.errors.customErrors;

public class LocationAlreadyExistsException extends RuntimeException {
    public LocationAlreadyExistsException(String message) {
        super(message);
    }
}
