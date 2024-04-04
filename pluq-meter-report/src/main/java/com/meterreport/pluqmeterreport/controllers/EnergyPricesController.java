package com.meterreport.pluqmeterreport.controllers;

import com.meterreport.pluqmeterreport.models.EnergyPrice;
import com.meterreport.pluqmeterreport.services.EnergyPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<EnergyPrice>> getAllEnergyPrices() {
        return ResponseEntity.ok(energyPriceService.getAllEnergyPrices());
    }

    @GetMapping
    public ResponseEntity<Optional<EnergyPrice>> getEnergyPriceById(@RequestParam String energyPriceId) {
        return ResponseEntity.ok(energyPriceService.getEnergyPriceById(energyPriceId));
    }


    @PostMapping
    public ResponseEntity<EnergyPrice> createEnergyPrice(@RequestBody EnergyPrice energyPrice) {
        return ResponseEntity.status(HttpStatus.CREATED).body(energyPriceService.saveEnergyPrice(energyPrice));
    }

    @PostMapping("/list")
    public ResponseEntity<List<EnergyPrice>> createEnergyPriceList(@RequestBody List<EnergyPrice> energyPrices) {
        return ResponseEntity.status(HttpStatus.CREATED).body(energyPriceService.saveEnergyPrices(energyPrices));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteEnergyPrice(@RequestParam String energyPriceId) {
        energyPriceService.deleteEnergyPrice(energyPriceId);
        return ResponseEntity.ok("Energy price deleted successfully");
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllEnergyPrice() {
        energyPriceService.deleteEnergyPrices();
        return ResponseEntity.ok("All energy prices deleted successfully");

    }

}
