package com.meterreport.pluqmeterreport.controllers;

import com.meterreport.pluqmeterreport.models.location.Location;
import com.meterreport.pluqmeterreport.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/all")
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping
    public Location getLocationById(@RequestParam String locationId){
        return locationService.getLocationById(locationId);
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
