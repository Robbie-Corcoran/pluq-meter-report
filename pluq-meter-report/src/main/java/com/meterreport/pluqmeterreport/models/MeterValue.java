package com.meterreport.pluqmeterreport.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "meterValues")
public class MeterValue {

    @Id
    private String transactionId;
    private String date;
    private String dateAdded;
    private String physicalReference;
    private double meterValue;
}
