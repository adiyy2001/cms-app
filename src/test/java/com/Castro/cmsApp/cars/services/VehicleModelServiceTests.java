package com.Castro.cmsApp.cars.services;

import com.Castro.cmsApp.cars.models.VehicleModel;
import com.Castro.cmsApp.cars.repositories.VehicleModelRepository;
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

class VehicleModelServiceTest {

    @Mock
    private VehicleModelRepository vehicleModelRepository;

    @InjectMocks
    private VehicleModelService vehicleModelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findAll should return all vehicle models")
    void findAll_ShouldReturnAllVehicleModels() {
        // Given
        List<VehicleModel> vehicleModels = new ArrayList<>();
        vehicleModels.add(new VehicleModel());
        vehicleModels.add(new VehicleModel());

        when(vehicleModelRepository.findAll()).thenReturn(vehicleModels);

        // When
        List<VehicleModel> result = vehicleModelService.findAll();

        // Then
        assertEquals(vehicleModels.size(), result.size());
        verify(vehicleModelRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findById with valid ID should return the corresponding vehicle model")
    void findById_WithValidId_ShouldReturnVehicleModel() {
        // Given
        int id = 1;
        VehicleModel vehicleModel = new VehicleModel();
        when(vehicleModelRepository.findById(id)).thenReturn(Optional.of(vehicleModel));

        // When
        Optional<VehicleModel> result = vehicleModelService.findById(id);

        // Then
        assertEquals(Optional.of(vehicleModel), result);
        verify(vehicleModelRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("findById with invalid ID should return empty optional")
    void findById_WithInvalidId_ShouldReturnEmptyOptional() {
        // Given
        int id = 1;
        when(vehicleModelRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Optional<VehicleModel> result = vehicleModelService.findById(id);

        // Then
        assertEquals(Optional.empty(), result);
        verify(vehicleModelRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("delete should delete the vehicle model")
    void delete_ShouldDeleteVehicleModel() {
        // Given
        int id = 1;

        // When
        vehicleModelService.delete(id);

        // Then
        verify(vehicleModelRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("save should save the vehicle model")
    void save_ShouldSaveVehicleModel() {
        // Given
        VehicleModel vehicleModel = new VehicleModel();

        // When
        vehicleModelService.save(vehicleModel);

        // Then
        verify(vehicleModelRepository, times(1)).save(vehicleModel);
    }
}
