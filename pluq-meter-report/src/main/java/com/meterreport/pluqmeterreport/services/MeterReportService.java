package com.meterreport.pluqmeterreport.services;

import com.meterreport.pluqmeterreport.models.report.MeterReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeterReportService {

    private final LocationService locationService;
    private final MeterValueService meterValueService;
    private final EnergyPriceService energyPriceService;

    @Autowired
    public MeterReportService(LocationService locationService, MeterValueService meterValueService, EnergyPriceService energyPriceService) {
        this.locationService = locationService;
        this.meterValueService = meterValueService;
        this.energyPriceService = energyPriceService;
    }

//    TODO: Implement report generation logic

    public MeterReport generateMeterReport() {
        return null;
    }

}
