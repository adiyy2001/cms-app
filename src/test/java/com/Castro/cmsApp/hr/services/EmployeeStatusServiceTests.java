package com.Castro.cmsApp.hr.services;

import com.Castro.cmsApp.hr.models.EmployeeStatus;
import com.Castro.cmsApp.hr.repositories.EmployeeStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeStatusServiceTests {

    private EmployeeStatusService employeeStatusService;

    @Mock
    private EmployeeStatusRepository employeeStatusRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeStatusService = new EmployeeStatusService(employeeStatusRepository);
    }

    @Test
    @DisplayName("Test findAll() method")
    public void testFindAll() {
        // Given
        List<EmployeeStatus> employeeStatuses = new ArrayList<>();
        employeeStatuses.add(new EmployeeStatus());
        employeeStatuses.add(new EmployeeStatus());

        when(employeeStatusRepository.findAll()).thenReturn(employeeStatuses);

        // When
        List<EmployeeStatus> result = employeeStatusService.findAll();

        // Then
        assertEquals(employeeStatuses, result);
    }

    @Test
    @DisplayName("Test findById() method")
    public void testFindById() {
        // Given
        EmployeeStatus employeeStatus = new EmployeeStatus();
        Optional<EmployeeStatus> optionalEmployeeStatus = Optional.of(employeeStatus);

        when(employeeStatusRepository.findById(1)).thenReturn(optionalEmployeeStatus);

        // When
        Optional<EmployeeStatus> result = employeeStatusService.findById(1);

        // Then
        assertEquals(optionalEmployeeStatus, result);
    }

    @Test
    @DisplayName("Test delete() method")
    public void testDelete() {
        // Given

        // When
        employeeStatusService.delete(1);

        // Then
        verify(employeeStatusRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Test save() method")
    public void testSave() {
        // Given
        EmployeeStatus employeeStatus = new EmployeeStatus();

        // When
        employeeStatusService.save(employeeStatus);

        // Then
        verify(employeeStatusRepository, times(1)).save(employeeStatus);
    }
}
