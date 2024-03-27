package com.meterreport.pluqmeterreport.services;

import com.meterreport.pluqmeterreport.models.location.Location;
import com.meterreport.pluqmeterreport.repos.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public List<Location> saveMultipleLocations(List<Location> locationsList) {
        return locationRepository.saveAll(locationsList);
    }

    public void delete(String locationId) {
        locationRepository.deleteById(locationId);
    }

    public void deleteAllLocations() {
        locationRepository.deleteAll();
    }

    public Optional<Location> getLocationById(String locationId) {
        return locationRepository.findById(locationId);
    }
}
