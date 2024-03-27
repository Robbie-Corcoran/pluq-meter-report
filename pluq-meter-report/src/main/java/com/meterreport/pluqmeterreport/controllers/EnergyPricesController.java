package com.meterreport.pluqmeterreport.controllers;

import com.meterreport.pluqmeterreport.models.EnergyPrice;
import com.meterreport.pluqmeterreport.services.EnergyPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/energy-prices")
public class EnergyPricesController {

    private final EnergyPriceService energyPriceService;

    @Autowired
    public EnergyPricesController(EnergyPriceService energyPriceService) {
        this.energyPriceService = energyPriceService;
    }

    @GetMapping("/all")
    public List<EnergyPrice> getAllEnergyPrices() {
        return energyPriceService.getAllEnergyPrices();
    }

    @GetMapping
    public Optional<EnergyPrice> getEnergyPriceById(@RequestParam String energyPriceId){
        return energyPriceService.getEnergyPriceById(energyPriceId);
    }


    @PostMapping
    public EnergyPrice createEnergyPrice(@RequestBody EnergyPrice energyPrice){
        return energyPriceService.saveEnergyPrice(energyPrice);
    }

    @PostMapping ("/list")
    public List<EnergyPrice> createEnergyPriceList(@RequestBody List<EnergyPrice> energyPrices){
        return energyPriceService.saveEnergyPrices(energyPrices);
    }

    @DeleteMapping
    public void deleteEnergyPrice(@RequestParam String energyPriceId) {
        energyPriceService.deleteEnergyPrice(energyPriceId);
    }

    @DeleteMapping("/all")
    public void deleteAllEnergyPrice() {
        energyPriceService.deleteEnergyPrices();
    }

}
