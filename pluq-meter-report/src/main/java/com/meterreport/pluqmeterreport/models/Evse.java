package com.meterreport.pluqmeterreport.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Evse {
    private String uid;
    private String evseId;
    private String status;
    private String lastUpdated;
    private List<String> capabilities;
    private String physicalReference;
    private List<Connector> connectors;

}
