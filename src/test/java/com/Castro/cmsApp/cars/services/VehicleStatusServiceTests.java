package com.Castro.cmsApp.cars.services;

import com.Castro.cmsApp.cars.models.VehicleStatus;
import com.Castro.cmsApp.cars.repositories.VehicleStatusRepository;
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

class VehicleStatusServiceTest {

    @Mock
    private VehicleStatusRepository vehicleStatusRepository;

    @InjectMocks
    private VehicleStatusService vehicleStatusService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findAll should return all vehicle statuses")
    void findAll_ShouldReturnAllVehicleStatuses() {
        // Given
        List<VehicleStatus> vehicleStatuses = new ArrayList<>();
        vehicleStatuses.add(new VehicleStatus());
        vehicleStatuses.add(new VehicleStatus());

        when(vehicleStatusRepository.findAll()).thenReturn(vehicleStatuses);

        // When
        List<VehicleStatus> result = vehicleStatusService.findAll();

        // Then
        assertEquals(vehicleStatuses.size(), result.size());
        verify(vehicleStatusRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findById with valid ID should return the corresponding vehicle status")
    void findById_WithValidId_ShouldReturnVehicleStatus() {
        // Given
        int id = 1;
        VehicleStatus vehicleStatus = new VehicleStatus();
        when(vehicleStatusRepository.findById(id)).thenReturn(Optional.of(vehicleStatus));

        // When
        Optional<VehicleStatus> result = vehicleStatusService.findById(id);

        // Then
        assertEquals(Optional.of(vehicleStatus), result);
        verify(vehicleStatusRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("findById with invalid ID should return an empty optional")
    void findById_WithInvalidId_ShouldReturnEmptyOptional() {
        // Given
        int id = 1;
        when(vehicleStatusRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Optional<VehicleStatus> result = vehicleStatusService.findById(id);

        // Then
        assertEquals(Optional.empty(), result);
        verify(vehicleStatusRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("delete should delete the vehicle status")
    void delete_ShouldDeleteVehicleStatus() {
        // Given
        int id = 1;

        // When
        vehicleStatusService.delete(id);

        // Then
        verify(vehicleStatusRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("save should save the vehicle status")
    void save_ShouldSaveVehicleStatus() {
        // Given
        VehicleStatus vehicleStatus = new VehicleStatus();

        // When
        vehicleStatusService.save(vehicleStatus);

        // Then
        verify(vehicleStatusRepository, times(1)).save(vehicleStatus);
    }
}
