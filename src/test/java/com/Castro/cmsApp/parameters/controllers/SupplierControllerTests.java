package com.Castro.cmsApp.parameters.controllers;

import com.Castro.cmsApp.parameters.models.Supplier;
import com.Castro.cmsApp.parameters.services.CountryService;
import com.Castro.cmsApp.parameters.services.StateService;
import com.Castro.cmsApp.parameters.services.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SupplierControllerTests {

    private SupplierController supplierController;

    @Mock
    private SupplierService supplierService;

    @Mock
    private CountryService countryService;

    @Mock
    private StateService stateService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        supplierController = new SupplierController(supplierService, countryService, stateService);
    }

    @Test
    @DisplayName("Test findAll() method")
    public void testFindAll() {
        // Given

        // When
        String viewName = supplierController.findAll(model);

        // Then
        assertEquals("/parameters/suppliers", viewName);
        verify(supplierService, times(1)).findAll();
        verify(countryService, times(1)).findAll();
        verify(stateService, times(1)).findAll();
        verify(model, times(1)).addAttribute("suppliers", supplierService.findAll());
        verify(model, times(1)).addAttribute("countries", countryService.findAll());
        verify(model, times(1)).addAttribute("states", stateService.findAll());
    }

    @Test
    @DisplayName("Test addSupplier() method")
    public void testAddSupplier() {
        // Given

        // When
        String viewName = supplierController.addSupplier(model);

        // Then
        assertEquals("parameters/supplierAdd", viewName);
        verify(countryService, times(1)).findAll();
        verify(model, times(1)).addAttribute("countries", countryService.findAll());
    }

    @Test
    @DisplayName("Test editSupplier() method")
    public void testEditSupplier() {
        // Given
        Integer id = 1;
        String op = "edit";
        Supplier supplier = new Supplier();

        when(supplierService.findById(id)).thenReturn(supplier);

        // When
        String viewName = supplierController.editSupplier(id, op, model);

        // Then
        assertEquals("/parameters/supplieredit", viewName);
        verify(supplierService, times(1)).findById(id);
        verify(supplierService, times(1)).findAll();
        verify(countryService, times(1)).findAll();
        verify(stateService, times(1)).findAll();
        verify(model, times(1)).addAttribute("supplier", supplier);
        verify(model, times(1)).addAttribute("suppliers", supplierService.findAll());
        verify(model, times(1)).addAttribute("countries", countryService.findAll());
        verify(model, times(1)).addAttribute("states", stateService.findAll());
    }

    @Test
    @DisplayName("Test save() method")
    public void testSave() {
        // Given
        Supplier supplier = new Supplier();

        // When
        String viewName = supplierController.save(supplier);

        // Then
        assertEquals("redirect:/parameters/suppliers", viewName);
        verify(supplierService, times(1)).save(supplier);
    }

    @Test
    @DisplayName("Test deleteById() method")
    public void testDeleteById() {
        // Given
        Integer id = 1;

        // When
        String viewName = supplierController.deleteById(id);

        // Then
        assertEquals("redirect:/parameters/suppliers", viewName);
        verify(supplierService, times(1)).deleteById(id);
    }
}
