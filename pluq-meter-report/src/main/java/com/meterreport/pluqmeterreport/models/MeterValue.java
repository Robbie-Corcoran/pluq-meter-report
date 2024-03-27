package com.meterreport.pluqmeterreport.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "meterValues")
public class MeterValue {

    @Id
    private String id;
    private String date;
    private String dateAdded;
    private String physicalReference;
    private String transactionId;
    private double meterValue;
}
