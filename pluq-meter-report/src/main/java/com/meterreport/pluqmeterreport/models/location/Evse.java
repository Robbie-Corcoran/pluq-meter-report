package com.meterreport.pluqmeterreport.models.location;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Evse {

    private String uid;
    @BsonProperty(value = "evse_id")
    private String evseId;
    private String status;
    @BsonProperty(value = "last_updated")
    private String lastUpdated;
    private List<String> capabilities;
    @BsonProperty(value = "physical_reference")
    private String physicalReference;
    private List<Connector> connectors;

}
