package com.meterreport.pluqmeterreport.controllers;

import com.meterreport.pluqmeterreport.models.location.Location;
import com.meterreport.pluqmeterreport.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }

    @GetMapping
    public List<Location> getAll() {
        return locationService.getAll();
    }

    @PostMapping
    public Location createLocation(@RequestBody Location location) {
        return locationService.saveLocation(location);
    }

    @PostMapping("/multiple")
    public List<Location> createMultipleLocations(@RequestBody List<Location> locationsList){
        return locationService.saveMultipleLocations(locationsList);
    }

    @DeleteMapping
    public void deleteLocation(String locationId) {
        locationService.delete(locationId);
    }

    @DeleteMapping("/all")
    public void deleteAllLocations() {
        locationService.deleteAllLocations();
    }
}
