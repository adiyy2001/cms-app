package com.Castro.cmsApp.parameters.controllers;

import com.Castro.cmsApp.parameters.models.State;
import com.Castro.cmsApp.parameters.services.CountryService;
import com.Castro.cmsApp.parameters.services.StateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StateControllerTests {

    private StateController stateController;

    @Mock
    private StateService stateService;

    @Mock
    private CountryService countryService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        stateController = new StateController(stateService, countryService);
    }

    @Test
    @DisplayName("Test findAll() method")
    public void testFindAll() {
        // Given

        // When
        String viewName = stateController.findAll(model);

        // Then
        assertEquals("/parameters/states", viewName);
        verify(stateService, times(1)).findAll();
        verify(countryService, times(1)).findAll();
        verify(model, times(1)).addAttribute("states", stateService.findAll());
        verify(model, times(1)).addAttribute("countries", countryService.findAll());
    }

    @Test
    @DisplayName("Test addState() method")
    public void testAddState() {
        // Given

        // When
        String viewName = stateController.addState(model);

        // Then
        assertEquals("parameters/stateAdd", viewName);
        verify(stateService, times(1)).findAll();
        verify(countryService, times(1)).findAll();
        verify(model, times(1)).addAttribute("states", stateService.findAll());
        verify(model, times(1)).addAttribute("countries", countryService.findAll());
    }

    @Test
    @DisplayName("Test editState() method")
    public void testEditState() {
        // Given
        Integer id = 1;
        String op = "edit";
        State state = new State();

        when(stateService.findById(id)).thenReturn(state);

        // When
        String viewName = stateController.editState(id, op, model);

        // Then
        assertEquals("/parameters/stateedit", viewName);
        verify(stateService, times(1)).findById(id);
        verify(stateService, times(1)).findAll();
        verify(countryService, times(1)).findAll();
        verify(model, times(1)).addAttribute("state", state);
        verify(model, times(1)).addAttribute("states", stateService.findAll());
        verify(model, times(1)).addAttribute("countries", countryService.findAll());
    }

    @Test
    @DisplayName("Test addNew() method")
    public void testAddNew() {
        // Given
        State state = new State();

        // When
        String viewName = stateController.addNew(state);

        // Then
        assertEquals("redirect:/parameters/states", viewName);
        verify(stateService, times(1)).save(state);
    }

    @Test
    @DisplayName("Test delete() method")
    public void testDelete() {
        // Given
        Integer id = 1;

        // When
        String viewName = stateController.delete(id);

        // Then
        assertEquals("redirect:/parameters/states", viewName);
        verify(stateService, times(1)).delete(id);
    }
}
