package com.meterreport.pluqmeterreport.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "energyPrices")
public class EnergyPrices {

    @Id
    private String id;
    private String country;
    private String buyVolume;
    private String sellVolume;
    private String price;
    private String currency;
    private String unit;
    private String date;
    private String timeslot;
}
