package com.meterreport.pluqmeterreport.repos;

import com.meterreport.pluqmeterreport.models.location.Evse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EvseRepository extends MongoRepository<Evse, String> {
}
