package com.Castro.cmsApp.cars.services;

import com.Castro.cmsApp.cars.models.VehicleMaintenance;
import com.Castro.cmsApp.cars.repositories.VehicleMaintenanceRepository;
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

class VehicleMaintenanceServiceTest {

    @Mock
    private VehicleMaintenanceRepository vehicleMaintenanceRepository;

    @InjectMocks
    private VehicleMaintenanceService vehicleMaintenanceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findAll should return all vehicle maintenances")
    void findAll_ShouldReturnAllVehicleMaintenances() {
        // Given
        List<VehicleMaintenance> vehicleMaintenances = new ArrayList<>();
        vehicleMaintenances.add(new VehicleMaintenance());
        vehicleMaintenances.add(new VehicleMaintenance());

        when(vehicleMaintenanceRepository.findAll()).thenReturn(vehicleMaintenances);

        // When
        List<VehicleMaintenance> result = vehicleMaintenanceService.findAll();

        // Then
        assertEquals(vehicleMaintenances.size(), result.size());
        verify(vehicleMaintenanceRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findById with valid ID should return the corresponding vehicle maintenance")
    void findById_WithValidId_ShouldReturnVehicleMaintenance() {
        // Given
        int id = 1;
        VehicleMaintenance vehicleMaintenance = new VehicleMaintenance();
        when(vehicleMaintenanceRepository.findById(id)).thenReturn(Optional.of(vehicleMaintenance));

        // When
        VehicleMaintenance result = vehicleMaintenanceService.findById(id);

        // Then
        assertEquals(vehicleMaintenance, result);
        verify(vehicleMaintenanceRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("findById with invalid ID should return null")
    void findById_WithInvalidId_ShouldReturnNull() {
        // Given
        int id = 1;
        when(vehicleMaintenanceRepository.findById(id)).thenReturn(Optional.empty());

        // When
        VehicleMaintenance result = vehicleMaintenanceService.findById(id);

        // Then
        assertEquals(null, result);
        verify(vehicleMaintenanceRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("delete should delete the vehicle maintenance")
    void delete_ShouldDeleteVehicleMaintenance() {
        // Given
        int id = 1;

        // When
        vehicleMaintenanceService.delete(id);

        // Then
        verify(vehicleMaintenanceRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("save should save the vehicle maintenance")
    void save_ShouldSaveVehicleMaintenance() {
        // Given
        VehicleMaintenance vehicleMaintenance = new VehicleMaintenance();

        // When
        vehicleMaintenanceService.save(vehicleMaintenance);

        // Then
        verify(vehicleMaintenanceRepository, times(1)).save(vehicleMaintenance);
    }
}
