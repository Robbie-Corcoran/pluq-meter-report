package com.meterreport.pluqmeterreport.models.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnergyPriceInfo {
    private String buyVolume;
    private String sellVolume;
    private String pricePerKWh;
    private String currency;
}
