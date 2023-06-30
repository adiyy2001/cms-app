package com.Castro.cmsApp.cars.controllers;

import com.Castro.cmsApp.cars.models.VehicleStatus;
import com.Castro.cmsApp.cars.services.VehicleStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("VehicleStatusController Tests")
class VehicleStatusControllerTests {
    @Mock
    private VehicleStatusService vehicleStatusService;

    @Mock
    private Model model;

    private VehicleStatusController vehicleStatusController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vehicleStatusController = new VehicleStatusController(vehicleStatusService);
    }

    @Test
    @DisplayName("findAll method should return the correct view name")
    void findAll_ShouldReturnCorrectViewName() {
        // Given
        List<VehicleStatus> vehicleStatuses = new ArrayList<>();
        when(vehicleStatusService.findAll()).thenReturn(vehicleStatuses);

        // When
        String viewName = vehicleStatusController.findAll(model);

        // Then
        assertEquals("/cars/vehicleStatuses", viewName);
        verify(model, times(1)).addAttribute("vehicleStatuses", vehicleStatuses);
    }

    @Test
    @DisplayName("findById method should return the optional vehicle status")
    void findById_ShouldReturnOptionalVehicleStatus() {
        // Given
        Integer id = 1;
        VehicleStatus vehicleStatus = new VehicleStatus();
        when(vehicleStatusService.findById(id)).thenReturn(Optional.of(vehicleStatus));

        // When
        Optional<VehicleStatus> result = vehicleStatusController.findById(id);

        // Then
        assertEquals(Optional.of(vehicleStatus), result);
    }

    @Test
    @DisplayName("addNew method should save the vehicle status and redirect correctly")
    void addNew_ShouldSaveVehicleStatusAndRedirect() {
        // Given
        VehicleStatus vehicleStatus = new VehicleStatus();

        // When
        String redirectUrl = vehicleStatusController.addNew(vehicleStatus);

        // Then
        verify(vehicleStatusService, times(1)).save(vehicleStatus);
        assertEquals("redirect:/cars/vehicleStatuses", redirectUrl);
    }

    @Test
    @DisplayName("delete method should delete the vehicle status and redirect correctly")
    void delete_ShouldDeleteVehicleStatusAndRedirect() {
        // Given
        Integer id = 1;

        // When
        String redirectUrl = vehicleStatusController.delete(id);

        // Then
        verify(vehicleStatusService, times(1)).delete(id);
        assertEquals("redirect:/cars/vehicleStatuses", redirectUrl);
    }

}