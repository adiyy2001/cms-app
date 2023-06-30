package com.Castro.cmsApp.parameters.controllers;

import com.Castro.cmsApp.parameters.models.Location;
import com.Castro.cmsApp.parameters.services.CountryService;
import com.Castro.cmsApp.parameters.services.LocationService;
import com.Castro.cmsApp.parameters.services.StateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LocationControllerTests {

    private LocationController locationController;

    @Mock
    private LocationService locationService;

    @Mock
    private CountryService countryService;

    @Mock
    private StateService stateService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        locationController = new LocationController(locationService, countryService, stateService);
    }

    @Test
    @DisplayName("Test findAll() method")
    public void testFindAll() {
        // Given
        List<Location> locations = new ArrayList<>();
        when(locationService.findAll()).thenReturn(locations);
        when(countryService.findAll()).thenReturn(new ArrayList<>());
        when(stateService.findAll()).thenReturn(new ArrayList<>());

        // When
        String viewName = locationController.findAll(model);

        // Then
        assertEquals("/parameters/locations", viewName);
        verify(locationService, times(1)).findAll();
        verify(countryService, times(1)).findAll();
        verify(stateService, times(1)).findAll();
        verify(model, times(1)).addAttribute("locations", locations);
        verify(model, times(1)).addAttribute("countries", new ArrayList<>());
        verify(model, times(1)).addAttribute("states", new ArrayList<>());
    }

    @Test
    @DisplayName("Test addLocation() method")
    public void testAddLocation() {
        // Given
        when(countryService.findAll()).thenReturn(new ArrayList<>());

        // When
        String viewName = locationController.addLocation(model);

        // Then
        assertEquals("parameters/locationAdd", viewName);
        verify(countryService, times(1)).findAll();
        verify(model, times(1)).addAttribute("countries", new ArrayList<>());
    }

    @Test
    @DisplayName("Test editLocation() method")
    public void testEditLocation() {
        // Given
        Integer id = 1;
        String op = "edit";
        Location location = new Location();
        when(locationService.findById(id)).thenReturn(location);
        when(countryService.findAll()).thenReturn(new ArrayList<>());
        when(stateService.findAll()).thenReturn(new ArrayList<>());

        // When
        String viewName = locationController.editLocation(id, op, model);

        // Then
        assertEquals("/parameters/locationedit", viewName);
        verify(locationService, times(1)).findById(id);
        verify(countryService, times(1)).findAll();
        verify(stateService, times(1)).findAll();
        verify(model, times(1)).addAttribute("location", location);
        verify(model, times(1)).addAttribute("countries", new ArrayList<>());
        verify(model, times(1)).addAttribute("states", new ArrayList<>());
    }

    @Test
    @DisplayName("Test save() method")
    public void testSave() {
        // Given
        Location location = new Location();

        // When
        String viewName = locationController.save(location);

        // Then
        assertEquals("redirect:/parameters/locations", viewName);
        verify(locationService, times(1)).save(location);
    }

    @Test
    @DisplayName("Test deleteById() method")
    public void testDeleteById() {
        // Given
        Integer id = 1;

        // When
        String viewName = locationController.deleteById(id);

        // Then
        assertEquals("redirect:/parameters/locations", viewName);
        verify(locationService, times(1)).deleteById(id);
    }
}
