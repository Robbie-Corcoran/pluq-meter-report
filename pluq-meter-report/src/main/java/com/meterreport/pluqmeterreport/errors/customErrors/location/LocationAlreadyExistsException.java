package com.meterreport.pluqmeterreport.errors.customErrors.location;

public class LocationAlreadyExistsException extends RuntimeException {
    public LocationAlreadyExistsException(String message) {
        super(message);
    }
}