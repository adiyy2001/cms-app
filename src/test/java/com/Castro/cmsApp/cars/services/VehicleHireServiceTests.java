package com.Castro.cmsApp.cars.services;

import com.Castro.cmsApp.cars.models.VehicleHire;
import com.Castro.cmsApp.cars.repositories.VehicleHireRepository;
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

class VehicleHireServiceTest {

    @Mock
    private VehicleHireRepository vehicleHireRepository;

    @InjectMocks
    private VehicleHireService vehicleHireService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findAll should return all vehicle hires")
    void findAll_ShouldReturnAllVehicleHires() {
        // Given
        List<VehicleHire> vehicleHires = new ArrayList<>();
        vehicleHires.add(new VehicleHire());
        vehicleHires.add(new VehicleHire());

        when(vehicleHireRepository.findAll()).thenReturn(vehicleHires);

        // When
        List<VehicleHire> result = vehicleHireService.findAll();

        // Then
        assertEquals(vehicleHires.size(), result.size());
        verify(vehicleHireRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findById with valid ID should return the corresponding vehicle hire")
    void findById_WithValidId_ShouldReturnVehicleHire() {
        // Given
        int id = 1;
        VehicleHire vehicleHire = new VehicleHire();
        when(vehicleHireRepository.findById(id)).thenReturn(Optional.of(vehicleHire));

        // When
        VehicleHire result = vehicleHireService.findById(id);

        // Then
        assertEquals(vehicleHire, result);
        verify(vehicleHireRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("findById with invalid ID should return null")
    void findById_WithInvalidId_ShouldReturnNull() {
        // Given
        int id = 1;
        when(vehicleHireRepository.findById(id)).thenReturn(Optional.empty());

        // When
        VehicleHire result = vehicleHireService.findById(id);

        // Then
        assertEquals(null, result);
        verify(vehicleHireRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("delete should delete the vehicle hire")
    void delete_ShouldDeleteVehicleHire() {
        // Given
        int id = 1;

        // When
        vehicleHireService.delete(id);

        // Then
        verify(vehicleHireRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("save should save the vehicle hire")
    void save_ShouldSaveVehicleHire() {
        // Given
        VehicleHire vehicleHire = new VehicleHire();

        // When
        vehicleHireService.save(vehicleHire);

        // Then
        verify(vehicleHireRepository, times(1)).save(vehicleHire);
    }
}
