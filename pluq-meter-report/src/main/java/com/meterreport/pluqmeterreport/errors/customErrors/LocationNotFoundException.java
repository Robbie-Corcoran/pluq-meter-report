package com.meterreport.pluqmeterreport.errors.customErrors;

public class LocationNotFoundException extends RuntimeException {

    public LocationNotFoundException(String message) {
        super(message);
    }
}
