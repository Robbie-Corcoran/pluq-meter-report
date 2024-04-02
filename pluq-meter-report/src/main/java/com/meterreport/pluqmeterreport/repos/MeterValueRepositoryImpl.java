package com.meterreport.pluqmeterreport.repos;

import com.meterreport.pluqmeterreport.models.MeterValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class MeterValueRepositoryImpl implements MeterValueRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<MeterValue> findAllByPhysicalReference(String physicalReference) {
        Query query = new Query();
        query.addCriteria(Criteria.where("physicalReference").is(physicalReference));
        return mongoTemplate.find(query, MeterValue.class);
    }
}
