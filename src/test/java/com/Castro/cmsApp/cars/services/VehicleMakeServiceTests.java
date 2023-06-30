package com.Castro.cmsApp.cars.services;

import com.Castro.cmsApp.cars.models.VehicleMake;
import com.Castro.cmsApp.cars.repositories.VehicleMakeRepository;
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

class VehicleMakeServiceTest {

    @Mock
    private VehicleMakeRepository vehicleMakeRepository;

    @InjectMocks
    private VehicleMakeService vehicleMakeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findAll should return all vehicle makes")
    void findAll_ShouldReturnAllVehicleMakes() {
        // Given
        List<VehicleMake> vehicleMakes = new ArrayList<>();
        vehicleMakes.add(new VehicleMake());
        vehicleMakes.add(new VehicleMake());

        when(vehicleMakeRepository.findAll()).thenReturn(vehicleMakes);

        // When
        List<VehicleMake> result = vehicleMakeService.findAll();

        // Then
        assertEquals(vehicleMakes.size(), result.size());
        verify(vehicleMakeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findById with valid ID should return the corresponding vehicle make")
    void findById_WithValidId_ShouldReturnVehicleMake() {
        // Given
        int id = 1;
        VehicleMake vehicleMake = new VehicleMake();
        when(vehicleMakeRepository.findById(id)).thenReturn(Optional.of(vehicleMake));

        // When
        Optional<VehicleMake> result = vehicleMakeService.findById(id);

        // Then
        assertEquals(Optional.of(vehicleMake), result);
        verify(vehicleMakeRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("findById with invalid ID should return an empty Optional")
    void findById_WithInvalidId_ShouldReturnEmptyOptional() {
        // Given
        int id = 1;
        when(vehicleMakeRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Optional<VehicleMake> result = vehicleMakeService.findById(id);

        // Then
        assertEquals(Optional.empty(), result);
        verify(vehicleMakeRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("delete should delete the vehicle make")
    void delete_ShouldDeleteVehicleMake() {
        // Given
        int id = 1;

        // When
        vehicleMakeService.delete(id);

        // Then
        verify(vehicleMakeRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("save should save the vehicle make")
    void save_ShouldSaveVehicleMake() {
        // Given
        VehicleMake vehicleMake = new VehicleMake();

        // When
        vehicleMakeService.save(vehicleMake);

        // Then
        verify(vehicleMakeRepository, times(1)).save(vehicleMake);
    }
}
