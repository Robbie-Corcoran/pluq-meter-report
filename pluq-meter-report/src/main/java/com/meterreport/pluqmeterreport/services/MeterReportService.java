package com.meterreport.pluqmeterreport.services;

import com.meterreport.pluqmeterreport.models.MeterReport;
import com.meterreport.pluqmeterreport.models.MeterValue;
import com.meterreport.pluqmeterreport.models.location.Evse;
import com.meterreport.pluqmeterreport.models.location.Location;
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

    @Autowired
    public MeterReportService(LocationService locationService, MeterValueService meterValueService) {
        this.locationService = locationService;
        this.meterValueService = meterValueService;
    }


    public List<MeterReport> generateMeterReportForAllLocations() {
        List<MeterReport> meterReportList = new ArrayList<>();
        List<Location> locationList = locationService.getAllLocations();

        for (Location location : locationList) {
            MeterReport meterReport = generateMeterReportByLocationId(location.getId());
            meterReportList.add(meterReport);
        }

        return meterReportList;
    }

    public MeterReport generateMeterReportByLocationId(String locationId) {
        MeterReport meterReport = new MeterReport();
        Location location = locationService.getLocationById(locationId);
        List<Evse> evseList = location.getEvses();

        setLocationDetails(meterReport, location);
        calculateAndSetMetrics(meterReport, evseList);

        meterReport.setReportComplete(true);
        if (meterReport.getLocationName().isEmpty() || meterReport.getLocationAddress().isEmpty() || meterReport.getNumberOfChargingSessions() == 0 || meterReport.getTotalKWhCharged() == 0) {
            meterReport.setReportComplete(false);
        }

        return meterReport;
    }

    private void setLocationDetails(MeterReport meterReport, Location location) {
        meterReport.setLocationName(location.getName());
        meterReport.setLocationAddress(location.getAddress() + ", " + location.getCity() + ", " + location.getCountry() + ".");
    }

    private void calculateAndSetMetrics(MeterReport meterReport, List<Evse> evseList) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.DOWN);

        double totalKWhCharged = calculateTotalKWhCharged(evseList);
        meterReport.setTotalKWhCharged(Double.parseDouble(df.format(totalKWhCharged)));

        int numberOfChargingSessions = calculateNumberOfChargingSessions(evseList);
        meterReport.setNumberOfChargingSessions(numberOfChargingSessions);

        double kWhPerSocket = totalKWhCharged / evseList.size();
        meterReport.setAverageKWhPerSocket(Double.parseDouble(df.format(kWhPerSocket)));

        double averageKWhPerSession = totalKWhCharged / numberOfChargingSessions;
        meterReport.setAverageKWhPerSession(Double.parseDouble(df.format(averageKWhPerSession)));

        double kWhPerDayPerSocket = calculateKWhPerDayPerSocket(evseList);
        meterReport.setAverageKWhPerDayPerSocket(Double.parseDouble(df.format(kWhPerDayPerSocket)));
    }

    public double calculateTotalKWhCharged(List<Evse> evseList) {
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

    public int calculateNumberOfChargingSessions(List<Evse> evseList) {
        int numberOfChargingSessions = 0;
        for (Evse evse : evseList) {
            List<MeterValue> meterValues = meterValueService.getMeterValuesByPhysicalReference(evse.getUid());
            numberOfChargingSessions += meterValues.size();
        }
        return numberOfChargingSessions;
    }

    public double calculateKWhPerDayPerSocket(List<Evse> evseList) {
        Map<String, List<MeterValue>> meterValuesByDay = groupMeterValuesByDay(evseList);
        double totalKwhChargedPerDayPerSocket = 0;
        int numberOfDays = meterValuesByDay.size();
        int numberOfSockets = evseList.size();

        for (List<MeterValue> meterValues : meterValuesByDay.values()) {
            double dailyTotalKwh = 0;
            for (MeterValue meterValue : meterValues) {
                dailyTotalKwh = meterValue.getMeterValue();
            }
            totalKwhChargedPerDayPerSocket += dailyTotalKwh;
        }

        return (totalKwhChargedPerDayPerSocket / numberOfSockets) / numberOfDays;
    }

    public Map<String, List<MeterValue>> groupMeterValuesByDay(List<Evse> evseList) {
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