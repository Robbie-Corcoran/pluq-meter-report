package com.meterreport.pluqmeterreport.controllers;

import com.meterreport.pluqmeterreport.models.location.Location;
import com.meterreport.pluqmeterreport.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Location>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @GetMapping
    public ResponseEntity<Location> getLocationById(@RequestParam String locationId) {
        return ResponseEntity.ok(locationService.getLocationById(locationId));

    }

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        return ResponseEntity.ok(locationService.saveLocation(location));
    }

    @PostMapping("/list")
    public ResponseEntity<List<Location>> createLocationsList(@RequestBody List<Location> locationsList) {
        return ResponseEntity.ok(locationService.saveLocationsList(locationsList));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLocation(@RequestParam String locationId) {
        locationService.delete(locationId);
        return ResponseEntity.ok("Location deleted successfully.");
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllLocations() {
        locationService.deleteAllLocations();
        return ResponseEntity.ok("All locations deleted successfully.");
    }
}
