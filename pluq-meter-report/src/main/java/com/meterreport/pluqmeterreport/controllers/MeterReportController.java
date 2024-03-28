package com.meterreport.pluqmeterreport.controllers;

import com.meterreport.pluqmeterreport.models.report.MeterReport;
import com.meterreport.pluqmeterreport.services.MeterReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meter-report")
public class MeterReportController {

    private final MeterReportService meterReportService;

    public MeterReportController(MeterReportService meterReportService) {
        this.meterReportService = meterReportService;
    }

    @GetMapping
    public MeterReport generateMeterReport() {
        return meterReportService.generateMeterReport();
    }
}
