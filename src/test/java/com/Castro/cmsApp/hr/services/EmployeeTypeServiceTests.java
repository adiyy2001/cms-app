package com.Castro.cmsApp.hr.services;

import com.Castro.cmsApp.hr.models.EmployeeType;
import com.Castro.cmsApp.hr.repositories.EmployeeTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeTypeServiceTests {

    private EmployeeTypeService employeeTypeService;

    @Mock
    private EmployeeTypeRepository employeeTypeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeTypeService = new EmployeeTypeService(employeeTypeRepository);
    }

    @Test
    @DisplayName("Test findAll() method")
    public void testFindAll() {
        // Given
        List<EmployeeType> employeeTypes = new ArrayList<>();
        employeeTypes.add(new EmployeeType());
        employeeTypes.add(new EmployeeType());

        when(employeeTypeRepository.findAll()).thenReturn(employeeTypes);

        // When
        List<EmployeeType> result = employeeTypeService.findAll();

        // Then
        assertEquals(employeeTypes, result);
    }

    @Test
    @DisplayName("Test findById() method")
    public void testFindById() {
        // Given
        EmployeeType employeeType = new EmployeeType();
        Optional<EmployeeType> optionalEmployeeType = Optional.of(employeeType);

        when(employeeTypeRepository.findById(1)).thenReturn(optionalEmployeeType);

        // When
        Optional<EmployeeType> result = employeeTypeService.findById(1);

        // Then
        assertEquals(optionalEmployeeType, result);
    }

    @Test
    @DisplayName("Test delete() method")
    public void testDelete() {
        // Given

        // When
        employeeTypeService.delete(1);

        // Then
        verify(employeeTypeRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Test save() method")
    public void testSave() {
        // Given
        EmployeeType employeeType = new EmployeeType();

        // When
        employeeTypeService.save(employeeType);

        // Then
        verify(employeeTypeRepository, times(1)).save(employeeType);
    }
}
