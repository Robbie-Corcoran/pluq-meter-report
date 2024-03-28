package com.meterreport.pluqmeterreport.services;

import com.meterreport.pluqmeterreport.models.EnergyPrice;
import com.meterreport.pluqmeterreport.repos.EnergyPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnergyPriceService {
    private final EnergyPriceRepository energyPriceRepository;

    @Autowired
    public EnergyPriceService(EnergyPriceRepository energyPriceRepository) {
        this.energyPriceRepository = energyPriceRepository;
    }

    public List<EnergyPrice> saveEnergyPrices(List<EnergyPrice> energyPrice) {
        return energyPriceRepository.saveAll(energyPrice);
    }

    public List<EnergyPrice> getAllEnergyPrices() {
        return energyPriceRepository.findAll();
    }

    public Optional<EnergyPrice> getEnergyPriceById(String energyPriceId) {
        return energyPriceRepository.findById(energyPriceId);
    }

    public EnergyPrice saveEnergyPrice(EnergyPrice energyPrice) {
        return energyPriceRepository.save(energyPrice);
    }

    public void deleteEnergyPrice(String energyPriceId) {
        energyPriceRepository.deleteById(energyPriceId);
    }

    public void deleteEnergyPrices() {
        energyPriceRepository.deleteAll();
    }
}
