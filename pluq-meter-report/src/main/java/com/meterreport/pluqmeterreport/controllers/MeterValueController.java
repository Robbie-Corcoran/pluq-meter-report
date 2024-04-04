package com.meterreport.pluqmeterreport.controllers;

import com.meterreport.pluqmeterreport.models.MeterValue;
import com.meterreport.pluqmeterreport.services.MeterValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/meter-values")
public class MeterValueController {

    private final MeterValueService meterValueService;

    @Autowired
    public MeterValueController(MeterValueService meterValueService) {
        this.meterValueService = meterValueService;
    }

    @GetMapping
    public ResponseEntity<Optional<MeterValue>> getMeterValueById(@RequestParam String meterValueId) {
        return ResponseEntity.ok(meterValueService.getMeterValueById(meterValueId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MeterValue>> getAllMeterValues() {
        return ResponseEntity.ok(meterValueService.getAllMeterValues());
    }

    @PostMapping
    public ResponseEntity<MeterValue> createMeterValue(@RequestBody MeterValue meterValue) {
        return ResponseEntity.status(HttpStatus.CREATED).body(meterValueService.saveMeterValue(meterValue));
    }

    @PostMapping("/list")
    public ResponseEntity<List<MeterValue>> createMeterValueList(@RequestBody List<MeterValue> meterValueList) {
        return ResponseEntity.status(HttpStatus.CREATED).body(meterValueService.saveMeterValueList(meterValueList));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMeterValue(@RequestParam String meterValueId) {
        meterValueService.deleteMeterValue(meterValueId);
        return ResponseEntity.ok("Meter value deleted successfully.");
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllMeterValues() {
        meterValueService.deleteAllMeterValues();
        return ResponseEntity.ok("All meter values deleted successfully.");
    }
}
