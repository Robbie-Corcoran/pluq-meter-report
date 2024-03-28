package com.meterreport.pluqmeterreport.controllers;

import com.meterreport.pluqmeterreport.models.MeterValue;
import com.meterreport.pluqmeterreport.services.MeterValueService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<MeterValue> getMeterValueById(@RequestParam String meterValueId) {
        return meterValueService.getMeterValueById(meterValueId);
    }

    @GetMapping("/all")
    public List<MeterValue> getAllMeterValues() {
        return meterValueService.getAllMeterValues();
    }

    @PostMapping
    public MeterValue createMeterValue(@RequestBody MeterValue meterValue) {
        return meterValueService.saveMeterValue(meterValue);
    }

    @PostMapping("/list")
    public List<MeterValue> createMeterValueList(@RequestBody List<MeterValue> meterValueList) {
        return meterValueService.saveMeterValueList(meterValueList);
    }

    @DeleteMapping
    public void deleteMeterValue(@RequestParam String meterValueId) {
        meterValueService.deleteMeterValue(meterValueId);
    }

    @DeleteMapping("/all")
    public void deleteAllMeterValues() {
        meterValueService.deleteAllMeterValues();
    }
}
