package com.Castro.cmsApp.parameters.services;

import com.Castro.cmsApp.parameters.models.Supplier;
import com.Castro.cmsApp.parameters.repositories.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("SupplierService Tests")
class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierService supplierService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findAll should return all suppliers")
    void findAll_ShouldReturnAllSuppliers() {
        // Given
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(new Supplier());
        suppliers.add(new Supplier());

        when(supplierRepository.findAll()).thenReturn(suppliers);

        // When
        List<Supplier> result = supplierService.findAll();

        // Then
        assertEquals(suppliers.size(), result.size());
        verify(supplierRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findById with valid ID should return the corresponding supplier")
    void findById_WithValidId_ShouldReturnSupplier() {
        // Given
        int id = 1;
        Supplier supplier = new Supplier();
        when(supplierRepository.findById(id)).thenReturn(Optional.of(supplier));

        // When
        Supplier result = supplierService.findById(id);

        // Then
        assertEquals(supplier, result);
        verify(supplierRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("findById with invalid ID should return null")
    void findById_WithInvalidId_ShouldReturnNull() {
        // Given
        int id = 1;
        when(supplierRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Supplier result = supplierService.findById(id);

        // Then
        assertEquals(null, result);
        verify(supplierRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("deleteById should delete the supplier")
    void deleteById_ShouldDeleteSupplier() {
        // Given
        int id = 1;

        // When
        supplierService.deleteById(id);

        // Then
        verify(supplierRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("save should save the supplier")
    void save_ShouldSaveSupplier() {
        // Given
        Supplier supplier = new Supplier();

        // When
        supplierService.save(supplier);

        // Then
        verify(supplierRepository, times(1)).save(supplier);
    }
}

