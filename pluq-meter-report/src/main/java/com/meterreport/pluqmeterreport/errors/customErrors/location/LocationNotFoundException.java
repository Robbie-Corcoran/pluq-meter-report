package com.meterreport.pluqmeterreport.errors.customErrors.location;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(String message) {
        super(message);
    }
}