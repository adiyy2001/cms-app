package com.Castro.cmsApp.cars.services;

import com.Castro.cmsApp.cars.models.VehicleType;
import com.Castro.cmsApp.cars.repositories.VehicleTypeRepository;
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

class VehicleTypeServiceTest {

    @Mock
    private VehicleTypeRepository vehicleTypeRepository;

    @InjectMocks
    private VehicleTypeService vehicleTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findAll should return all vehicle types")
    void findAll_ShouldReturnAllVehicleTypes() {
        // Given
        List<VehicleType> vehicleTypes = new ArrayList<>();
        vehicleTypes.add(new VehicleType());
        vehicleTypes.add(new VehicleType());

        when(vehicleTypeRepository.findAll()).thenReturn(vehicleTypes);

        // When
        List<VehicleType> result = vehicleTypeService.findAll();

        // Then
        assertEquals(vehicleTypes.size(), result.size());
        verify(vehicleTypeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findById with valid ID should return the corresponding vehicle type")
    void findById_WithValidId_ShouldReturnVehicleType() {
        // Given
        int id = 1;
        VehicleType vehicleType = new VehicleType();
        when(vehicleTypeRepository.findById(id)).thenReturn(Optional.of(vehicleType));

        // When
        Optional<VehicleType> result = vehicleTypeService.findById(id);

        // Then
        assertEquals(Optional.of(vehicleType), result);
        verify(vehicleTypeRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("findById with invalid ID should return an empty optional")
    void findById_WithInvalidId_ShouldReturnEmptyOptional() {
        // Given
        int id = 1;
        when(vehicleTypeRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Optional<VehicleType> result = vehicleTypeService.findById(id);

        // Then
        assertEquals(Optional.empty(), result);
        verify(vehicleTypeRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("delete should delete the vehicle type")
    void delete_ShouldDeleteVehicleType() {
        // Given
        int id = 1;

        // When
        vehicleTypeService.delete(id);

        // Then
        verify(vehicleTypeRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("save should save the vehicle type")
    void save_ShouldSaveVehicleType() {
        // Given
        VehicleType vehicleType = new VehicleType();

        // When
        vehicleTypeService.save(vehicleType);

        // Then
        verify(vehicleTypeRepository, times(1)).save(vehicleType);
    }
}
