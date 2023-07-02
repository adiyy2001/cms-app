package com.Castro.cmsApp.cars.controllers;

import com.Castro.cmsApp.cars.models.VehicleType;
import com.Castro.cmsApp.cars.services.VehicleTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("VehicleTypeController Tests")
class VehicleTypeControllerTests {

    @Mock
    private VehicleTypeService vehicleTypeService;

    @Mock
    private Model model;

    private VehicleTypeController vehicleTypeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vehicleTypeController = new VehicleTypeController(vehicleTypeService);
    }

    @Test
    @DisplayName("findAll method should return the correct view name")
    void findAll_ShouldReturnCorrectViewName() {
        // Given
        List<VehicleType> vehicleTypes = new ArrayList<>();
        when(vehicleTypeService.findAll()).thenReturn(vehicleTypes);

        // When
        String viewName = vehicleTypeController.findAll(model);

        // Then
        assertEquals("/cars/vehicleTypes", viewName);
        verify(model, times(1)).addAttribute("vehicleTypes", vehicleTypes);
    }

    @Test
    @DisplayName("findById method should return the optional vehicle type")
    void findById_ShouldReturnOptionalVehicleType() {
        // Given
        Integer id = 1;
        VehicleType vehicleType = new VehicleType();
        when(vehicleTypeService.findById(id)).thenReturn(Optional.of(vehicleType));

        // When
        Optional<VehicleType> result = vehicleTypeController.findById(id);

        // Then
        assertEquals(Optional.of(vehicleType), result);
    }

    @Test
    @DisplayName("addNew method should save the vehicle type and redirect correctly")
    void addNew_ShouldSaveVehicleTypeAndRedirect() {
        // Given
        VehicleType vehicleType = new VehicleType();

        // When
        String redirectUrl = vehicleTypeController.addNew(vehicleType);

        // Then
        verify(vehicleTypeService, times(1)).save(vehicleType);
        assertEquals("redirect:/cars/vehicleTypes", redirectUrl);
    }

    @Test
    @DisplayName("delete method should delete the vehicle type and redirect correctly")
    void delete_ShouldDeleteVehicleTypeAndRedirect() {
        // Given
        Integer id = 1;

        // When
        String redirectUrl = vehicleTypeController.delete(id);

        // Then
        verify(vehicleTypeService, times(1)).delete(id);
        assertEquals("redirect:/cars/vehicleTypes", redirectUrl);
    }
}

