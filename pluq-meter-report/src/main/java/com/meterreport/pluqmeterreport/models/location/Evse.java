package com.meterreport.pluqmeterreport.models.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Evse {

    private String uid;
    @JsonProperty("evse_id")
    private String evseId;
    private String status;
    @JsonProperty("last_updated")
    private String lastUpdated;
    private List<String> capabilities;
    @JsonProperty("physical_reference")
    private String physicalReference;
    private List<Connector> connectors;

}
