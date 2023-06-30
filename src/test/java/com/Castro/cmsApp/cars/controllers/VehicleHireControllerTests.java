package com.Castro.cmsApp.cars.controllers;

import com.Castro.cmsApp.cars.models.VehicleHire;
import com.Castro.cmsApp.cars.services.VehicleHireService;
import com.Castro.cmsApp.cars.services.VehicleService;
import com.Castro.cmsApp.parameters.services.ClientService;
import com.Castro.cmsApp.parameters.services.LocationService;
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

@DisplayName("VehicleHireController Tests")
class VehicleHireControllerTests {

    @Mock
    private VehicleHireService vehicleHireService;

    @Mock
    private ClientService clientService;

    @Mock
    private LocationService locationService;

    @Mock
    private VehicleService vehicleService;

    @Mock
    private Model model;

    private VehicleHireController vehicleHireController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vehicleHireController = new VehicleHireController(
                vehicleHireService,
                clientService,
                locationService,
                vehicleService
        );
    }

    @Test
    @DisplayName("findAll method should return the correct view name")
    void findAll_ShouldReturnCorrectViewName() {
        // Given
        List<VehicleHire> vehicleHires = new ArrayList<>();
        when(vehicleHireService.findAll()).thenReturn(vehicleHires);

        // When
        String viewName = vehicleHireController.findAll(model);

        // Then
        assertEquals("/cars/hires", viewName);
        verify(model, times(1)).addAttribute("hires", vehicleHires);
    }

    @Test
    @DisplayName("addHire method should return the correct view name")
    void addHire_ShouldReturnCorrectViewName() {
        // Given
        when(clientService.findAll()).thenReturn(new ArrayList<>());
        when(locationService.findAll()).thenReturn(new ArrayList<>());
        when(vehicleService.findAll()).thenReturn(new ArrayList<>());

        // When
        String viewName = vehicleHireController.addHire(model);

        // Then
        assertEquals("/cars/hireAdd", viewName);
        verify(model, times(1)).addAttribute("clients", new ArrayList<>());
        verify(model, times(1)).addAttribute("locations", new ArrayList<>());
        verify(model, times(1)).addAttribute("vehicles", new ArrayList<>());
    }

    @Test
    @DisplayName("editHire method should return the correct view name")
    void editHire_ShouldReturnCorrectViewName() {
        // Given
        Integer hireId = 1;
        String operation = "update";
        VehicleHire hire = new VehicleHire();
        when(vehicleHireService.findById(hireId)).thenReturn(hire);
        when(clientService.findAll()).thenReturn(new ArrayList<>());
        when(locationService.findAll()).thenReturn(new ArrayList<>());
        when(vehicleService.findAll()).thenReturn(new ArrayList<>());

        // When
        String viewName = vehicleHireController.editHire(model, hireId, operation);

        // Then
        assertEquals("/cars/hire" + operation, viewName);
        verify(model, times(1)).addAttribute("hire", hire);
        verify(model, times(1)).addAttribute("clients", new ArrayList<>());
        verify(model, times(1)).addAttribute("locations", new ArrayList<>());
        verify(model, times(1)).addAttribute("vehicles", new ArrayList<>());
    }

    @Test
    @DisplayName("addNew method should save the vehicle hire and redirect to the correct URL")
    void addNew_ShouldSaveVehicleHireAndRedirectToCorrectURL() {
        // Given
        VehicleHire vehicleHire = new VehicleHire();

        // When
        String redirectURL = vehicleHireController.addNew(vehicleHire);

        // Then
        assertEquals("redirect:/cars/hires", redirectURL);
        verify(vehicleHireService, times(1)).save(vehicleHire);
    }

    @Test
    @DisplayName("delete method should delete the vehicle hire and redirect to the correct URL")
    void delete_ShouldDeleteVehicleHireAndRedirectToCorrectURL() {
        // Given
        Integer hireId = 1;

        // When
        String redirectURL = vehicleHireController.delete(hireId);

        // Then
        assertEquals("redirect:/cars/hires", redirectURL);
        verify(vehicleHireService, times(1)).delete(hireId);
    }
}

