package com.meterreport.pluqmeterreport.models.location;

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
    private String powerType;

}
