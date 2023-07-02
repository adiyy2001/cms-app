package com.Castro.cmsApp.parameters.services;

import com.Castro.cmsApp.parameters.models.Location;
import com.Castro.cmsApp.parameters.repositories.LocationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LocationServiceTests {

    private LocationRepository locationRepository;
    private LocationService locationService;

    @BeforeEach
    public void setup() {
        locationRepository = Mockito.mock(LocationRepository.class);
        locationService = new LocationService(locationRepository);
    }

    @Test
    @DisplayName("Test findAll()")
    public void findAll_ShouldReturnAllLocations() {
        // Create sample locations
        Location location1 = new Location(1, "Description 1", "Details 1", null, null, null, null, "City 1",
                "Address 1");
        Location location2 = new Location(2, "Description 2", "Details 2", null, null, null, null, "City 2",
                "Address 2");
        List<Location> locations = new ArrayList<>();
        locations.add(location1);
        locations.add(location2);

        // Mock the behavior of locationRepository.findAll()
        Mockito.when(locationRepository.findAll()).thenReturn(locations);

        // Call the service method
        List<Location> result = locationService.findAll();

        // Verify the result
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(location1, result.get(0));
        Assertions.assertEquals(location2, result.get(1));
    }

    @Test
    @DisplayName("Test findById()")
    public void findById_ShouldReturnLocationWithMatchingId() {
        // Create a sample location
        Location location = new Location(1, "Description", "Details", null, null, null, null, "City", "Address");

        // Mock the behavior of locationRepository.findById()
        Mockito.when(locationRepository.findById(1)).thenReturn(Optional.of(location));

        // Call the service method
        Location result = locationService.findById(1);

        // Verify the result
        Assertions.assertEquals(location, result);
    }

    @Test
    @DisplayName("Test save()")
    public void save_ShouldSaveLocation() {
        // Create a sample location
        Location location = new Location(1, "Description", "Details", null, null, null, null, "City", "Address");

        // Call the service method
        locationService.save(location);

        // Verify that locationRepository.save() was called with the location object
        Mockito.verify(locationRepository, Mockito.times(1)).save(location);
    }

    @Test
    @DisplayName("Test deleteById()")
    public void deleteById_ShouldDeleteLocationWithMatchingId() {
        // Call the service method
        locationService.deleteById(1);

        // Verify that locationRepository.deleteById() was called with the correct id
        Mockito.verify(locationRepository, Mockito.times(1)).deleteById(1);
    }
}
