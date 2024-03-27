package com.meterreport.pluqmeterreport.services;

import com.meterreport.pluqmeterreport.models.EnergyPrices;
import com.meterreport.pluqmeterreport.models.location.Evse;
import com.meterreport.pluqmeterreport.repos.EnergyPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnergyPriceService {
    private final EnergyPriceRepository energyPriceRepository;

    @Autowired
    public EnergyPriceService(EnergyPriceRepository energyPriceRepository) {
        this.energyPriceRepository = energyPriceRepository;
    }

    public EnergyPrices saveEnergyPrices(EnergyPrices energyPrices) {
        return energyPriceRepository.save(energyPrices);
    }
}
