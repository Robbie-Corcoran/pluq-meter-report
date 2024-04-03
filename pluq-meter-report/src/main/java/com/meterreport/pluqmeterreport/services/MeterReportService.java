package com.meterreport.pluqmeterreport.services;

import com.meterreport.pluqmeterreport.models.MeterValue;
import com.meterreport.pluqmeterreport.models.location.Evse;
import com.meterreport.pluqmeterreport.models.location.Location;
import com.meterreport.pluqmeterreport.models.report.MeterReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.DOWN);

        Location location = locationService.getLocationById(locationId);
        List<Evse> evseList = location.getEvses();

//            Name
        meterReport.setLocationName(location.getName());

//            Address
        meterReport.setLocationAddress(location.getAddress() + ", " + location.getCity() + ", " + location.getCountry() + ".");

//            Number of charging sockets
        meterReport.setNumberOfChargingSockets(evseList.size());

//            Total kWh charged
        double totalKWhCharged = calculateTotalKWhCharged(evseList);
        meterReport.setTotalKWhCharged(Double.parseDouble(df.format(totalKWhCharged)));

//            Number of charging sessions
        int numberOfChargingSessions = calculateNumberOfChargingSessions(evseList);
        meterReport.setNumberOfChargingSessions(numberOfChargingSessions);

//            Average kWh per socket
        double kWhPerSocket = totalKWhCharged / evseList.size();
        meterReport.setAverageKWhPerSocket(Double.parseDouble(df.format(kWhPerSocket)));

//            Average kWh per session
        double averageKWhPerSession = totalKWhCharged / numberOfChargingSessions;
        meterReport.setAverageKWhPerSession(Double.parseDouble(df.format(averageKWhPerSession)));

//            Calculate kWh per day per socket
        double kWhPerDayPerSocket = calculateKWhPerDayPerSocket(evseList);
        meterReport.setAverageKWhPerDayPerSocket(kWhPerDayPerSocket);

        return meterReport;
    }

    private double calculateTotalKWhCharged(List<Evse> evseList) {
        double locationTotalKwhCharged = 0;
        String previousUid = null;

        for (Evse evse : evseList) {
            if (previousUid == null || !previousUid.equals(evse.getUid().substring(0, evse.getUid().length() - 2))) {

                List<MeterValue> meterValues = meterValueService.getMeterValuesByPhysicalReference(evse.getUid());
                double evseTotalKwhCharged = 0;

                for (MeterValue meterValue : meterValues) {
                    evseTotalKwhCharged = meterValue.getMeterValue();
                }
                locationTotalKwhCharged += evseTotalKwhCharged;
                previousUid = evse.getUid();
            }

        }

        return locationTotalKwhCharged;
    }

    private int calculateNumberOfChargingSessions(List<Evse> evseList) {
        int numberOfChargingSessions = 0;
        for (Evse evse : evseList) {
            List<MeterValue> meterValues = meterValueService.getMeterValuesByPhysicalReference(evse.getUid());
            numberOfChargingSessions += meterValues.size();
        }
        return numberOfChargingSessions;
    }

    private double calculateKWhPerDayPerSocket(List<Evse> evseList) {
        Map<String, List<MeterValue>> meterValuesByDay = groupMeterValuesByDay(evseList);
        double totalKwhChargedPerDayPerSocket = 0;
        int numberOfDays = meterValuesByDay.size();

        for (List<MeterValue> meterValues : meterValuesByDay.values()) {
            double dailyTotalKwh = 0;
            for (MeterValue meterValue : meterValues) {
                dailyTotalKwh = meterValue.getMeterValue();
            }
            totalKwhChargedPerDayPerSocket += dailyTotalKwh;
        }

        return (totalKwhChargedPerDayPerSocket / evseList.size()) / numberOfDays;
    }

    private Map<String, List<MeterValue>> groupMeterValuesByDay(List<Evse> evseList) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        Map<String, List<MeterValue>> meterValuesByDay = new HashMap<>();

        for (Evse evse : evseList) {
            List<MeterValue> meterValues = meterValueService.getMeterValuesByPhysicalReference(evse.getUid());
            Map<String, List<MeterValue>> groupedByDay = meterValues.stream()
                    .collect(Collectors.groupingBy(meterValue -> {
                        LocalDate date = LocalDate.parse(meterValue.getDate(), formatter);
                        return date.toString();
                    }));

            for (Map.Entry<String, List<MeterValue>> entry : groupedByDay.entrySet()) {
                meterValuesByDay.computeIfAbsent(entry.getKey(), k -> new ArrayList<>()).addAll(entry.getValue());
            }
        }

        return meterValuesByDay;
    }

}