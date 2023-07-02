package com.Castro.cmsApp.cars.controllers;

import com.Castro.cmsApp.cars.models.VehicleModel;
import com.Castro.cmsApp.cars.services.VehicleModelService;
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

@DisplayName("VehicleModelController Tests")
class VehicleModelControllerTests {
    @Mock
    private VehicleModelService vehicleModelService;

    @Mock
    private Model model;

    private VehicleModelController vehicleModelController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vehicleModelController = new VehicleModelController(vehicleModelService);
    }

    @Test
    @DisplayName("findAll method should return the correct view name")
    void findAll_ShouldReturnCorrectViewName() {
        // Given
        List<VehicleModel> vehicleModels = new ArrayList<>();
        when(vehicleModelService.findAll()).thenReturn(vehicleModels);

        // When
        String viewName = vehicleModelController.findAll(model);

        // Then
        assertEquals("/cars/vehicleModels", viewName);
        verify(model, times(1)).addAttribute("vehicleModels", vehicleModels);
    }

    @Test
    @DisplayName("findById method should return the optional vehicle model")
    void findById_ShouldReturnOptionalVehicleModel() {
        // Given
        Integer id = 1;
        VehicleModel vehicleModel = new VehicleModel();
        when(vehicleModelService.findById(id)).thenReturn(Optional.of(vehicleModel));

        // When
        Optional<VehicleModel> result = vehicleModelController.findById(id);

        // Then
        assertEquals(Optional.of(vehicleModel), result);
    }

    @Test
    @DisplayName("addNew method should save the vehicle model and redirect correctly")
    void addNew_ShouldSaveVehicleModelAndRedirect() {
        // Given
        VehicleModel vehicleModel = new VehicleModel();

        // When
        String redirectUrl = vehicleModelController.addNew(vehicleModel);

        // Then
        verify(vehicleModelService, times(1)).save(vehicleModel);
        assertEquals("redirect:/cars/vehicleModels", redirectUrl);
    }

    @Test
    @DisplayName("delete method should delete the vehicle model and redirect correctly")
    void delete_ShouldDeleteVehicleModelAndRedirect() {
        // Given
        Integer id = 1;

        // When
        String redirectUrl = vehicleModelController.delete(id);

        // Then
        verify(vehicleModelService, times(1)).delete(id);
        assertEquals("redirect:/cars/vehicleModels", redirectUrl);
    }
}