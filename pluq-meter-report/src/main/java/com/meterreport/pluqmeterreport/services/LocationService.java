package com.meterreport.pluqmeterreport.services;

import com.meterreport.pluqmeterreport.errors.customErrors.LocationNotFoundException;
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

    public Optional<Location> getLocationById(String locationId) throws LocationNotFoundException {
        Optional<Location> location = locationRepository.findById(locationId);

        if (location.isEmpty()) {
            throw new LocationNotFoundException("Location not found by that locationId.");
        }
        return location;
    }

    public List<Location> getAllLocations() {
        List<Location> locationList = locationRepository.findAll();

        if (locationList.isEmpty()) {
            throw new LocationNotFoundException("Location list not found.");
        }

        return locationList;
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public List<Location> saveLocationsList(List<Location> locationsList) {
        return locationRepository.saveAll(locationsList);
    }

    public void delete(String locationId) {
        locationRepository.deleteById(locationId);
    }

    public void deleteAllLocations() {
        locationRepository.deleteAll();
    }
}
