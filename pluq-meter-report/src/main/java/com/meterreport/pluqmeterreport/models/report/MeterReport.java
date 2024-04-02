package com.meterreport.pluqmeterreport.models.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MeterReport {

    private String locationName;
    private String locationAddress;
    private int numberOfChargingSockets;
    private double totalKWhCharged;
    private int numberOfChargingSessions;
    private double averageKWhPerSocket;
    private double averageKWhPerSession;
    private double averageKWhPerDayPerSocket;
}
