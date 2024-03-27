package com.meterreport.pluqmeterreport.models.location;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "locations")
public class Location {

    @Id
    private String id;
    private String name;
    private String type;
    private String address;
    private String city;
    @BsonProperty(value = "postal_code")
    private String postalCode;
    private String country;
    private Coordinates coordinates;
    @BsonProperty(value = "charging_when_closed")
    private boolean chargingWhenClosed;
    @BsonProperty(value = "last_updated")
    private String lastUpdated;
    private List<Evse> evses;

}
