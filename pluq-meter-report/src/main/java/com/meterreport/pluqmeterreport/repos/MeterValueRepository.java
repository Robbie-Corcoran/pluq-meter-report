package com.meterreport.pluqmeterreport.repos;

import com.meterreport.pluqmeterreport.models.MeterValue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterValueRepository extends MongoRepository<MeterValue, String> {
}
