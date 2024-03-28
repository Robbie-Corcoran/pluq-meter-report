package com.meterreport.pluqmeterreport.controllers;

import com.meterreport.pluqmeterreport.models.MeterValue;
import com.meterreport.pluqmeterreport.services.MeterValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Optional<MeterValue> getMeterValueById(String meterValueId) {
        return meterValueService.getMeterValueById(meterValueId);
    }

    @GetMapping("/all")
    public List<MeterValue> getAllMeterValues() {
        return meterValueService.getAllMeterValues();
    }
}
