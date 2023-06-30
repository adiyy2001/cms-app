package com.Castro.cmsApp.parameters.controllers;

import com.Castro.cmsApp.parameters.models.Country;
import com.Castro.cmsApp.parameters.services.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CountryControllerTests {

    private CountryController countryController;

    @Mock
    private CountryService countryService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        countryController = new CountryController(countryService);
    }

    @Test
    @DisplayName("Test getOnePage() method")
    public void testGetOnePage() {
        // Given
        int currentPage = 1;
        Page<Country> page = mock(Page.class);
        List<Country> countries = new ArrayList<>();
        when(page.getTotalPages()).thenReturn(2);
        when(page.getTotalElements()).thenReturn(10L);
        when(page.getContent()).thenReturn(countries);
        when(countryService.findPage(currentPage)).thenReturn(page);

        // When
        String viewName = countryController.getOnePage(model, currentPage);

        // Then
        assertEquals("/parameters/countries", viewName);
        verify(countryService, times(1)).findPage(currentPage);
        verify(model, times(1)).addAttribute("currentPage", currentPage);
        verify(model, times(1)).addAttribute("totalPages", 2);
        verify(model, times(1)).addAttribute("totalItems", 10L);
        verify(model, times(1)).addAttribute("countries", countries);
    }

    @Test
    @DisplayName("Test getPageWithSort() method")
    public void testGetPageWithSort() {
        // Given
        int currentPage = 1;
        String field = "name";
        String sortDir = "asc";
        Page<Country> page = mock(Page.class);
        List<Country> countries = new ArrayList<>();
        when(page.getTotalPages()).thenReturn(2);
        when(page.getTotalElements()).thenReturn(10L);
        when(page.getContent()).thenReturn(countries);
        when(countryService.findAllWithSort(field, sortDir, currentPage)).thenReturn(page);

        // When
        String viewName = countryController.getPageWithSort(model, currentPage, field, sortDir);

        // Then
        assertEquals("/parameters/countries", viewName);
        verify(countryService, times(1)).findAllWithSort(field, sortDir, currentPage);
        verify(model, times(1)).addAttribute("currentPage", currentPage);
        verify(model, times(1)).addAttribute("totalPages", 2);
        verify(model, times(1)).addAttribute("totalItems", 10L);
        verify(model, times(1)).addAttribute("sortDir", sortDir);
        verify(model, times(1)).addAttribute("reverseSortDir", "desc");
        verify(model, times(1)).addAttribute("countries", countries);
    }

    @Test
    @DisplayName("Test getCountry() method")
    public void testGetCountry() {
        // Given
        int id = 1;
        Country country = new Country();
        when(countryService.getById(id)).thenReturn(country);

        // When
        Country result = countryController.getCountry(id);

        // Then
        assertEquals(country, result);
        verify(countryService, times(1)).getById(id);
    }

    @Test
    @DisplayName("Test editCountry() method")
    public void testEditCountry() {
        // Given
        int id = 1;
        String op = "Edit";
        Country country = new Country();
        when(countryService.getById(id)).thenReturn(country);

        // When
        String viewName = countryController.editCountry(id, op, model);

        // Then
        assertEquals("/parameters/countryEdit", viewName);
        verify(countryService, times(1)).getById(id);
        verify(model, times(1)).addAttribute("country", country);
    }

    @Test
    @DisplayName("Test save() method")
    public void testSave() {
        // Given
        Country country = new Country();

        // When
        String viewName = countryController.save(country);

        // Then
        assertEquals("redirect:/parameters/countries", viewName);
        verify(countryService, times(1)).save(country);
    }

    @Test
    @DisplayName("Test delete() method")
    public void testDelete() {
        // Given
        int id = 1;

        // When
        String viewName = countryController.delete(id);

        // Then
        assertEquals("redirect:/parameters/countries", viewName);
        verify(countryService, times(1)).delete(id);
    }
}

