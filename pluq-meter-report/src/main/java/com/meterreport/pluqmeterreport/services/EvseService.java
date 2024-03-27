package com.meterreport.pluqmeterreport.services;

import com.meterreport.pluqmeterreport.models.location.Evse;
import com.meterreport.pluqmeterreport.repos.EvseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvseService {

    private final EvseRepository evseRepository;

    @Autowired
    public EvseService(EvseRepository evseRepository) {
        this.evseRepository = evseRepository;
    }

    public Evse saveEvse(Evse evse) {
        return evseRepository.save(evse);
    }
}
