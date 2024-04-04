package com.meterreport.pluqmeterreport.errorsExceptions.customExceptions.location;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(String message) {
        super(message);
    }
}