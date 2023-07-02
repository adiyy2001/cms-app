package com.Castro.cmsApp.cars.controllers;

import com.Castro.cmsApp.cars.models.VehicleMovement;
import com.Castro.cmsApp.cars.services.VehicleMovementService;
import com.Castro.cmsApp.cars.services.VehicleService;
import com.Castro.cmsApp.parameters.services.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("VehicleMovementController Tests")
class VehicleMovementControllerTests {

    @Mock
    private VehicleMovementService vehicleMovementService;

    @Mock
    private LocationService locationService;

    @Mock
    private VehicleService vehicleService;

    @Mock
    private Model model;

    private VehicleMovementController vehicleMovementController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vehicleMovementController = new VehicleMovementController(
                vehicleMovementService,
                locationService,
                vehicleService
        );
    }

    @Test
    @DisplayName("findAll method should return the correct view name")
    void findAll_ShouldReturnCorrectViewName() {
        // Given

        // When
        String viewName = vehicleMovementController.findAll(model);

        // Then
        assertEquals("cars/movements", viewName);
        verify(model, times(1)).addAttribute("movements", vehicleMovementService.findAll());
    }

    @Test
    @DisplayName("addMovement method should return the correct view name")
    void addMovement_ShouldReturnCorrectViewName() {
        // Given

        // When
        String viewName = vehicleMovementController.addMovement(model);

        // Then
        assertEquals("/cars/movementAdd", viewName);
        verify(vehicleService, times(1)).findAll();
        verify(locationService, times(2)).findAll();
        verify(model, times(1)).addAttribute("locations1", locationService.findAll());
        verify(model, times(1)).addAttribute("locations2", locationService.findAll());
        verify(model, times(1)).addAttribute("vehicles", vehicleService.findAll());
    }

    @Test
    @DisplayName("editMovement method should return the correct view name")
    void editMovement_ShouldReturnCorrectViewName() {
        // Given
        Integer id = 1;
        String op = "op";
        VehicleMovement movement = new VehicleMovement();
        when(vehicleMovementService.findById(id)).thenReturn(movement);

        // When
        String viewName = vehicleMovementController.editMovement(model, id, op);

        // Then
        assertEquals("/cars/movement" + op, viewName);
        verify(vehicleMovementService, times(1)).findById(id);
        verify(vehicleService, times(1)).findAll();
        verify(locationService, times(2)).findAll();
        verify(model, times(1)).addAttribute("movement", movement);
        verify(model, times(1)).addAttribute("locations1", locationService.findAll());
        verify(model, times(1)).addAttribute("locations2", locationService.findAll());
        verify(model, times(1)).addAttribute("vehicles", vehicleService.findAll());
    }

    @Test
    @DisplayName("addNew method should save the vehicle movement and redirect correctly")
    void addNew_ShouldSaveVehicleMovementAndRedirect() {
        // Given
        VehicleMovement vehicleMovement = new VehicleMovement();

        // When
        String redirectUrl = vehicleMovementController.addNew(vehicleMovement);

        // Then
        verify(vehicleMovementService, times(1)).save(vehicleMovement);
        assertEquals("redirect:/cars/movements", redirectUrl);
    }

    @Test
    @DisplayName("delete method should delete the vehicle movement and redirect correctly")
    void delete_ShouldDeleteVehicleMovementAndRedirect() {
        // Given
        Integer id = 1;

        // When
        String redirectUrl = vehicleMovementController.delete(id);

        // Then
        verify(vehicleMovementService, times(1)).delete(id);
        assertEquals("redirect:/cars/movements", redirectUrl);
    }
}
