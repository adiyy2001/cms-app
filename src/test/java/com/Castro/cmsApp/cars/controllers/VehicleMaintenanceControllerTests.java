package com.Castro.cmsApp.cars.controllers;

import com.Castro.cmsApp.cars.models.VehicleMaintenance;
import com.Castro.cmsApp.cars.services.VehicleMaintenanceService;
import com.Castro.cmsApp.cars.services.VehicleService;
import com.Castro.cmsApp.parameters.services.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

@DisplayName("VehicleMaintenanceController Tests")
class VehicleMaintenanceControllerTests {
    @Mock
    private VehicleMaintenanceService vehicleMaintenanceService;

    @Mock
    private VehicleService vehicleService;

    @Mock
    private SupplierService supplierService;

    @Mock
    private Model model;

    private VehicleMaintenanceController vehicleMaintenanceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vehicleMaintenanceController = new VehicleMaintenanceController(vehicleMaintenanceService, vehicleService,
                supplierService);
    }

    @Test
    @DisplayName("addModelAttributes method should add vehicles and suppliers to the model")
    void addModelAttributes_ShouldAddVehiclesAndSuppliersToModel() {
        // When
        Model result = vehicleMaintenanceController.addModelAttributes(model);

        // Then
        verify(model, times(1)).addAttribute("vehicles", vehicleService.findAll());
        verify(model, times(1)).addAttribute("suppliers", supplierService.findAll());
        assertEquals(model, result);
    }

@Test
@DisplayName("findAll method should return the correct view name")
void findAll_ShouldReturnCorrectViewName() {
    // Given
    when(vehicleMaintenanceService.findAll()).thenReturn(new ArrayList<>());

    // When
    String viewName = vehicleMaintenanceController.findAll(model);

    // Then
    assertEquals("/cars/maintenances", viewName);
    verify(model, times(1)).addAttribute("maintenances", new ArrayList<>());
}

@Test
@DisplayName("addMaintenance method should add model attributes and return the correct view name")
void addMaintenance_ShouldAddModelAttributesAndReturnCorrectViewName() {
    // Set up
    VehicleMaintenance expectedMaintenance = new VehicleMaintenance();
    when(vehicleMaintenanceService.findById(1)).thenReturn(expectedMaintenance);

    // When
    String viewName = vehicleMaintenanceController.addMaintenance(model);

    // Then
assertEquals("/cars/maintenanceAdd", viewName);
}


    @Test
    @DisplayName("addNew method should save the vehicle maintenance and redirect correctly")
    void addNew_ShouldSaveVehicleMaintenanceAndRedirect() {
        // Given
        VehicleMaintenance vehicleMaintenance = new VehicleMaintenance();

        // When
        String redirectUrl = vehicleMaintenanceController.addNew(vehicleMaintenance);

        // Then
        verify(vehicleMaintenanceService, times(1)).save(vehicleMaintenance);
        assertEquals("redirect:/cars/maintenances", redirectUrl);
    }

    @Test
    @DisplayName("delete method should delete the vehicle maintenance and redirect correctly")
    void delete_ShouldDeleteVehicleMaintenanceAndRedirect() {
        // Given
        Integer id = 1;

        // When
        String redirectUrl = vehicleMaintenanceController.delete(id);

        // Then
        verify(vehicleMaintenanceService, times(1)).delete(id);
        assertEquals("redirect:/cars/maintenances", redirectUrl);
    }

    @Test
@DisplayName("editMaintenance method should update the model and return the correct view name")
void editMaintenance_ShouldUpdateModelAndReturnCorrectViewName() {
    // Given
    Integer id = 1;
    String op = "op";
    VehicleMaintenance maintenance = new VehicleMaintenance();
    when(vehicleMaintenanceService.findById(id)).thenReturn(maintenance);

    // When
    String viewName = vehicleMaintenanceController.editMaintenance(model, id, op);

    // Then
    assertEquals("/cars/maintenance" + op, viewName);
}

}