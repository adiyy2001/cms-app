package com.Castro.cmsApp.hr.controllers;

import com.Castro.cmsApp.hr.models.EmployeeStatus;
import com.Castro.cmsApp.hr.services.EmployeeStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("EmployeeStatusController Tests")
class EmployeeStatusControllerTests {

    @Mock
    private EmployeeStatusService employeeStatusService;

    @InjectMocks
    private EmployeeStatusController employeeStatusController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("parameters should return employeeStatuses view with all employee statuses")
    void parameters_ShouldReturnEmployeeStatusesViewWithAllEmployeeStatuses() {
        // Given
        List<EmployeeStatus> employeeStatuses = new ArrayList<>();
        employeeStatuses.add(new EmployeeStatus());
        employeeStatuses.add(new EmployeeStatus());
        when(employeeStatusService.findAll()).thenReturn(employeeStatuses);

        Model model = mock(Model.class);

        // When
        String result = employeeStatusController.parameters(model);

        // Then
        assertEquals("hr/employeeStatuses", result);
        verify(model, times(1)).addAttribute(eq("employeeStatuses"), eq(employeeStatuses));
    }

    @Test
    @DisplayName("getById should return the specified employee status")
    void getById_ShouldReturnSpecifiedEmployeeStatus() {
        // Given
        int employeeStatusId = 1;
        EmployeeStatus employeeStatus = new EmployeeStatus();
        when(employeeStatusService.findById(employeeStatusId)).thenReturn(Optional.of(employeeStatus));

        // When
        EmployeeStatus result = employeeStatusController.getById(employeeStatusId);

        // Then
        assertEquals(employeeStatus, result);
    }

    @Test
    @DisplayName("getById should return null if employee status is not found")
    void getById_ShouldReturnNullIfEmployeeStatusNotFound() {
        // Given
        int employeeStatusId = 1;
        when(employeeStatusService.findById(employeeStatusId)).thenReturn(Optional.empty());

        // When
        EmployeeStatus result = employeeStatusController.getById(employeeStatusId);

        // Then
        assertEquals(null, result);
    }

    @Test
    @DisplayName("save should save the employee status and redirect to employeeStatuses view")
    void save_ShouldSaveEmployeeStatusAndRedirectToEmployeeStatusesView() {
        // Given
        EmployeeStatus employeeStatus = new EmployeeStatus();

        // When
        String result = employeeStatusController.save(employeeStatus);

        // Then
        assertEquals("redirect:/hr/employeeStatuses", result);
        verify(employeeStatusService, times(1)).save(employeeStatus);
    }

    @Test
    @DisplayName("delete should delete the specified employee status and redirect to employeeStatuses view")
    void delete_ShouldDeleteSpecifiedEmployeeStatusAndRedirectToEmployeeStatusesView() {
        // Given
        int employeeStatusId = 1;

        // When
        String result = employeeStatusController.delete(employeeStatusId);

        // Then
        assertEquals("redirect:/hr/employeeStatuses", result);
        verify(employeeStatusService, times(1)).delete(employeeStatusId);
    }
}
