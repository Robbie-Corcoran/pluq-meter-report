package com.meterreport.pluqmeterreport.repos;

import com.meterreport.pluqmeterreport.models.EnergyPrices;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnergyPrice extends MongoRepository<EnergyPrices, String> {
}
