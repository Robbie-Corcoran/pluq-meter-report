package com.meterreport.pluqmeterreport.errors;

import com.meterreport.pluqmeterreport.errors.customErrors.location.LocationAlreadyExistsException;
import com.meterreport.pluqmeterreport.errors.customErrors.location.LocationNotFoundException;
import com.meterreport.pluqmeterreport.errors.customErrors.meterValue.MeterValueAlreadyExistsException;
import com.meterreport.pluqmeterreport.errors.customErrors.meterValue.MeterValueNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRuntimeError(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());
    }

    @ExceptionHandler({LocationNotFoundException.class})
    public ResponseEntity<Object> handleLocationNotFoundException(LocationNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler({LocationAlreadyExistsException.class})
    public ResponseEntity<Object> handleLocationAlreadyExistsException(LocationAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler({MeterValueNotFoundException.class})
    public ResponseEntity<Object> handleMeterValueNotFoundException(MeterValueNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler({MeterValueAlreadyExistsException.class})
    public ResponseEntity<Object> handleMeterValueAlreadyExistsException(MeterValueAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
}