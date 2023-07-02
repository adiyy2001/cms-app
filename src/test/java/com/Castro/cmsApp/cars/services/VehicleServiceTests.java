package com.Castro.cmsApp.cars.services;

import com.Castro.cmsApp.cars.models.Vehicle;
import com.Castro.cmsApp.cars.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findAll should return all vehicles")
    void findAll_ShouldReturnAllVehicles() {
        // Given
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle());
        vehicles.add(new Vehicle());

        when(vehicleRepository.findAll()).thenReturn(vehicles);

        // When
        List<Vehicle> result = vehicleService.findAll();

        // Then
        assertEquals(vehicles.size(), result.size());
        verify(vehicleRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findById with valid ID should return the corresponding vehicle")
    void findById_WithValidId_ShouldReturnVehicle() {
        // Given
        int id = 1;
        Vehicle vehicle = new Vehicle();
        when(vehicleRepository.findById(id)).thenReturn(Optional.of(vehicle));

        // When
        Vehicle result = vehicleService.findById(id);

        // Then
        assertEquals(vehicle, result);
        verify(vehicleRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("findById with invalid ID should return null")
    void findById_WithInvalidId_ShouldReturnNull() {
        // Given
        int id = 1;
        when(vehicleRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Vehicle result = vehicleService.findById(id);

        // Then
        assertEquals(null, result);
        verify(vehicleRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("delete should delete the vehicle")
    void delete_ShouldDeleteVehicle() {
        // Given
        int id = 1;

        // When
        vehicleService.delete(id);

        // Then
        verify(vehicleRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("save should save the vehicle")
    void save_ShouldSaveVehicle() {
        // Given
        Vehicle vehicle = new Vehicle();

        // When
        vehicleService.save(vehicle);

        // Then
        verify(vehicleRepository, times(1)).save(vehicle);
    }
}
