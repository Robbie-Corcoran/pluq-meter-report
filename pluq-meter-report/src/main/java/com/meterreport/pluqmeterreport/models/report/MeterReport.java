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
    private List<EvseInfo> evse;
    private double totalKWhCharged;
    private int numberOfChargingSessions;
    private double averageKWhPerSession;
    private List<EnergyPriceInfo> energyPrices;
    private double totalTransactionalCost;
    private double averageCostPerKWh;
}
