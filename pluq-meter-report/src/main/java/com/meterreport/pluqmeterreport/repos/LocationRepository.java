package com.meterreport.pluqmeterreport.repos;

import com.meterreport.pluqmeterreport.models.location.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends MongoRepository<Location, String> {

    Location getLocationById(String LocationId);

}
