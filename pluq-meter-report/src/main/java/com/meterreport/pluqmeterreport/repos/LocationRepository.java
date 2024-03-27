package com.meterreport.pluqmeterreport.repos;

import com.meterreport.pluqmeterreport.models.location.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location, String> {
}
