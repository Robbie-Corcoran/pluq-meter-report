package com.meterreport.pluqmeterreport.models.location;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
@Setter
@NoArgsConstructor
public class Connector {
    private String id;
    private String standard;
    private String format;
    @BsonProperty(value = "power_type")
    private String powerType;
    private int voltage;
    private int amperage;
    @BsonProperty(value = "last_updated")
    private String lastUpdated;
    @BsonProperty(value = "tariff_id")
    private String tariffId;

}
