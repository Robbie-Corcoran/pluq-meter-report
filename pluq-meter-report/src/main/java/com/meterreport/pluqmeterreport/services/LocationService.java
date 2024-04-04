package com.meterreport.pluqmeterreport.services;

import com.meterreport.pluqmeterreport.errors.customErrors.LocationAlreadyExistsException;
import com.meterreport.pluqmeterreport.errors.customErrors.LocationNotFoundException;
import com.meterreport.pluqmeterreport.models.location.Location;
import com.meterreport.pluqmeterreport.repos.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new LocationNotFoundException("Location not found by locationId: " + locationId);
        }
        return location;
    }

    public List<Location> getAllLocations() throws LocationNotFoundException {
        List<Location> locationList = locationRepository.findAll();

        if (locationList.isEmpty()) {
            throw new LocationNotFoundException("Could not find any saved locations.");
        }

        return locationList;
    }

    public Location saveLocation(Location location) throws LocationAlreadyExistsException {
        if (locationRepository.findById(location.getId()).isPresent()) {
            throw new LocationAlreadyExistsException("Location already exists with id: " + location.getId());
        }

        return locationRepository.save(location);
    }

    //    TODO: Finish exception handling for this method.
    public List<Location> saveLocationsList(List<Location> locationsList) {
        return locationRepository.saveAll(locationsList);
    }

    public void delete(String locationId) throws LocationNotFoundException {
        if (locationRepository.findById(locationId).isEmpty()) {
            throw new LocationNotFoundException("Location not found by locationId: " + locationId);
        }
        locationRepository.deleteById(locationId);
    }

    public void deleteAllLocations() throws LocationNotFoundException {
        if (locationRepository.findAll().isEmpty()) {
            throw new LocationNotFoundException("Could not find any saved locations.");
        }
        locationRepository.deleteAll();
    }

    public Location getLocationById(String locationId) {
        return locationRepository.getLocationById(locationId);
    }
}
