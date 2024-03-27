package com.meterreport.pluqmeterreport.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MeterValue extends MongoRepository<MeterValue, String> {
}
