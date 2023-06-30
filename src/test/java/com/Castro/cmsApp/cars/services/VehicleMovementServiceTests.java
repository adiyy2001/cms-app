package com.Castro.cmsApp.cars.services;

import com.Castro.cmsApp.cars.models.VehicleMovement;
import com.Castro.cmsApp.cars.repositories.VehicleMovementRepository;
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

class VehicleMovementServiceTest {

    @Mock
    private VehicleMovementRepository vehicleMovementRepository;

    @InjectMocks
    private VehicleMovementService vehicleMovementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findAll should return all vehicle movements")
    void findAll_ShouldReturnAllVehicleMovements() {
        // Given
        List<VehicleMovement> vehicleMovements = new ArrayList<>();
        vehicleMovements.add(new VehicleMovement());
        vehicleMovements.add(new VehicleMovement());

        when(vehicleMovementRepository.findAll()).thenReturn(vehicleMovements);

        // When
        List<VehicleMovement> result = vehicleMovementService.findAll();

        // Then
        assertEquals(vehicleMovements.size(), result.size());
        verify(vehicleMovementRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findById with valid ID should return the corresponding vehicle movement")
    void findById_WithValidId_ShouldReturnVehicleMovement() {
        // Given
        int id = 1;
        VehicleMovement vehicleMovement = new VehicleMovement();
        when(vehicleMovementRepository.findById(id)).thenReturn(Optional.of(vehicleMovement));

        // When
        VehicleMovement result = vehicleMovementService.findById(id);

        // Then
        assertEquals(vehicleMovement, result);
        verify(vehicleMovementRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("findById with invalid ID should return null")
    void findById_WithInvalidId_ShouldReturnNull() {
        // Given
        int id = 1;
        when(vehicleMovementRepository.findById(id)).thenReturn(Optional.empty());

        // When
        VehicleMovement result = vehicleMovementService.findById(id);

        // Then
        assertEquals(null, result);
        verify(vehicleMovementRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("delete should delete the vehicle movement")
    void delete_ShouldDeleteVehicleMovement() {
        // Given
        int id = 1;

        // When
        vehicleMovementService.delete(id);

        // Then
        verify(vehicleMovementRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("save should save the vehicle movement")
    void save_ShouldSaveVehicleMovement() {
        // Given
        VehicleMovement vehicleMovement = new VehicleMovement();

        // When
        vehicleMovementService.save(vehicleMovement);

        // Then
        verify(vehicleMovementRepository, times(1)).save(vehicleMovement);
    }
}
