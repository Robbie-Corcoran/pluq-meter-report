package com.meterreport.pluqmeterreport.repos;

import com.meterreport.pluqmeterreport.models.EnergyPrices;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergyPriceRepository extends MongoRepository<EnergyPrices, String> {
}
