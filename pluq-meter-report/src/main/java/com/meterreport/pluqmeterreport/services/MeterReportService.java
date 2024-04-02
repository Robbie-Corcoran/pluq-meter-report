package com.meterreport.pluqmeterreport.services;

import com.meterreport.pluqmeterreport.models.MeterValue;
import com.meterreport.pluqmeterreport.models.location.Evse;
import com.meterreport.pluqmeterreport.models.location.Location;
import com.meterreport.pluqmeterreport.models.report.MeterReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public MeterReport generateMeterReportByLocationId(String locationId) {
        MeterReport meterReport = new MeterReport();
        Location location = locationService.getLocationById(locationId);
//            Name
            meterReport.setLocationName(location.getName());

//            Address
            meterReport.setLocationAddress(location.getAddress());

//            Total kWh charged
            double totalKWhCharged = calculateTotalKWhCharged(location);
            meterReport.setTotalKWhCharged(totalKWhCharged);

//            Number of charging sessions
            int numberOfChargingSessions = calculateNumberOfChargingSessions(location);
            meterReport.setNumberOfChargingSessions(numberOfChargingSessions);

//            Average kWh per socket
            double kWhPerSocket = totalKWhCharged / location.getEvses().size();
            meterReport.setAverageKWhPerSocket(kWhPerSocket);

//            Average kWh per session
            double averageKWhPerSession = totalKWhCharged / numberOfChargingSessions;
            meterReport.setAverageKWhPerSession(averageKWhPerSession);

//            Calculate kWh per day per socket
            double kWhPerDayPerSocket = calculateKWhPerDayPerSocket(location);
            meterReport.setAverageKWhPerDayPerSocket(kWhPerDayPerSocket);

        return meterReport;
    }

    private double calculateTotalKWhCharged(Location location) {
        double totalKwhCharged = 0;

        for (Evse evse : location.getEvses()) {
            List<MeterValue> meterValues = meterValueService.getMeterValuesByPhysicalReference(evse.getUid());
            for (MeterValue meterValue : meterValues) {
                totalKwhCharged += meterValue.getMeterValue();
            }
        }

        return totalKwhCharged;
    }

    private int calculateNumberOfChargingSessions(Location location) {
        return 0;
    }

    private double calculateKWhPerDayPerSocket(Location location) {
        return 0;
    }

}
