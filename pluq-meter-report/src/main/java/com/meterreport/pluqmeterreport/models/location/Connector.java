package com.meterreport.pluqmeterreport.models.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Connector {
    private String id;
    private String standard;
    private String format;
    @JsonProperty("power_type")
    private String powerType;
    private int voltage;
    private int amperage;
    @JsonProperty("last_updated")
    private String lastUpdated;
    @JsonProperty("tariff_id")
    private String tariffId;

}
