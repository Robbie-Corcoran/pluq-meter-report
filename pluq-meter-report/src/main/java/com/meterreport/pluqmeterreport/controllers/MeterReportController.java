package com.meterreport.pluqmeterreport.controllers;

import com.meterreport.pluqmeterreport.models.report.MeterReport;
import com.meterreport.pluqmeterreport.services.MeterReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/meter-report")
public class MeterReportController {

    private final MeterReportService meterReportService;


    public MeterReportController(MeterReportService meterReportService) {
        this.meterReportService = meterReportService;
    }

    @GetMapping
    public MeterReport generateMeterReportByLocationId(@RequestParam String locationId) {
        return meterReportService.generateMeterReportByLocationId(locationId);
    }

    @GetMapping("/all")
    public List<MeterReport> generateMeterReport() {
        return meterReportService.generateMeterReportForAllLocations();
    }
}
