package com.Castro.cmsApp.cars.controllers;

import com.Castro.cmsApp.cars.models.Vehicle;
import com.Castro.cmsApp.cars.services.*;
import com.Castro.cmsApp.hr.services.EmployeeService;
import com.Castro.cmsApp.parameters.services.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("VehicleController Tests")
class VehicleControllerTests {

    @Mock
    private VehicleService vehicleService;
    @Mock
    private VehicleTypeService vehicleTypeService;
    @Mock
    private VehicleMakeService vehicleMakeService;
    @Mock
    private VehicleModelService vehicleModelService;
    @Mock
    private LocationService locationService;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private VehicleStatusService vehicleStatusService;

    private VehicleController vehicleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        vehicleController = new VehicleController(
                vehicleService,
                vehicleTypeService,
                vehicleMakeService,
                vehicleModelService,
                locationService,
                employeeService,
                vehicleStatusService
        );
    }

    @Test
    @DisplayName("findAll method should return the correct view name")
    void findAll_ShouldReturnCorrectViewName() {
        // Given
        Model model = mock(Model.class);

        // When
        String viewName = vehicleController.findAll(model);

        // Then
        assertEquals("/cars/vehicles", viewName);
    }

    @Test
    @DisplayName("Add Vehicle - Should return correct view name")
    void addVehicle_ShouldReturnCorrectViewName() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        // Set up the mock dependencies
        // ...

        // Act
        String viewName = vehicleController.addNew(vehicle);

        // Assert
        assertEquals("redirect:/cars/vehicles", viewName);
    }

    @Test
    @DisplayName("editVehicle method should return the correct view name")
    void editVehicle_ShouldReturnCorrectViewName() {
        // Given
        Integer id = 1;
        String op = "Edit";
        Model model = mock(Model.class);
        Vehicle vehicle = new Vehicle();

        when(vehicleService.findById(id)).thenReturn(vehicle);

        // When
        String viewName = vehicleController.editVehicle(id, op, model);

        // Then
        assertEquals("/cars/vehicleEdit", viewName);
    }

    @Test
    @DisplayName("addNew method should save the vehicle and redirect correctly")
    void addNew_ShouldSaveVehicleAndRedirect() {
        // Given
        Vehicle vehicle = new Vehicle();

        // When
        String redirectUrl = vehicleController.addNew(vehicle);

        // Then
        assertEquals("redirect:/cars/vehicles", redirectUrl);
    }

    @Test
    @DisplayName("delete method should delete the vehicle and redirect correctly")
    void delete_ShouldDeleteVehicleAndRedirect() {
        // Given
        Integer id = 1;

        // When
        String redirectUrl = vehicleController.delete(id);

        // Then
        assertEquals("redirect:/cars/vehicles", redirectUrl);
    }

    // Add more tests for other methods in the VehicleController class

    // ...
}
