package com.meterreport.pluqmeterreport.services;

import com.meterreport.pluqmeterreport.errors.customErrors.location.LocationAlreadyExistsException;
import com.meterreport.pluqmeterreport.errors.customErrors.location.LocationNotFoundException;
import com.meterreport.pluqmeterreport.models.location.Location;
import com.meterreport.pluqmeterreport.repos.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location getLocationById(String locationId) {
        Location location = locationRepository.getLocationById(locationId);

        if (Objects.isNull(location)) {
            throw new LocationNotFoundException("Location not found by locationId: " + locationId);
        }

        return location;
    }

    public List<Location> getAllLocations() {
        List<Location> locationList = locationRepository.findAll();

        if (locationList.isEmpty()) {
            throw new LocationNotFoundException("Could not find any saved locations.");
        }

        return locationList;
    }

    public Location saveLocation(Location location) {
        if (locationRepository.findById(location.getId()).isPresent()) {
            throw new LocationAlreadyExistsException("Location already exists with id: " + location.getId());
        }

        return locationRepository.save(location);
    }

    public List<Location> saveLocationsList(List<Location> locationsList) {
        for (Location location : locationsList) {
            if (locationRepository.findById(location.getId()).isPresent()) {
                throw new LocationAlreadyExistsException("Location already exists with id: " + location.getId());
            }
        }
        return locationRepository.saveAll(locationsList);
    }

    public void delete(String locationId) {
        if (locationRepository.findById(locationId).isEmpty()) {
            throw new LocationNotFoundException("Location not found by locationId: " + locationId);
        }

        locationRepository.deleteById(locationId);
    }

    public void deleteAllLocations() {
        if (locationRepository.findAll().isEmpty()) {
            throw new LocationNotFoundException("Could not find any saved locations.");
        }
        locationRepository.deleteAll();
    }


}
