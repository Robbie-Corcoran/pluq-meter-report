package com.meterreport.pluqmeterreport.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MeterValue extends MongoRepository<MeterValue, String> {
}
