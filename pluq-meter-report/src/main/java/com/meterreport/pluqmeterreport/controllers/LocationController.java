package com.meterreport.pluqmeterreport.controllers;

import com.meterreport.pluqmeterreport.errors.customErrors.LocationNotFoundException;
import com.meterreport.pluqmeterreport.models.location.Location;
import com.meterreport.pluqmeterreport.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/location")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }


    @GetMapping
    public ResponseEntity<Optional<Location>> getLocationById(@RequestParam String locationId){
        try {
            return ResponseEntity.ok(locationService.getLocationById(locationId));
        } catch (LocationNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Location>> getAllLocations() {
        try {
            return ResponseEntity.of(Optional.ofNullable(locationService.getAllLocations()));
        } catch (LocationNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Location createLocation(@RequestBody Location location) {
        return locationService.saveLocation(location);
    }

    @PostMapping("/list")
    public List<Location> createLocationsList(@RequestBody List<Location> locationsList){
        return locationService.saveLocationsList(locationsList);
    }

    @DeleteMapping
    public void deleteLocation(@RequestParam String locationId) {
        locationService.delete(locationId);
    }

    @DeleteMapping("/all")
    public void deleteAllLocations() {
        locationService.deleteAllLocations();
    }
}
