package com.Castro.cmsApp.hr.controllers;

import com.Castro.cmsApp.hr.models.EmployeeType;
import com.Castro.cmsApp.hr.repositories.EmployeeTypeRepository;
import com.Castro.cmsApp.hr.services.EmployeeTypeService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@DisplayName("EmployeeTypeController Tests")
class EmployeeTypeControllerTests {

    @Mock
    private EmployeeTypeRepository employeeTypeRepository;

    @Mock
    private EmployeeTypeService employeeTypeService;

    @InjectMocks
    private EmployeeTypeController employeeTypeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("parameters should return employeeTypes view with all employee types")
    void parameters_ShouldReturnEmployeeTypesViewWithAllEmployeeTypes() {
        // Given
        List<EmployeeType> employeeTypes = new ArrayList<>();
        employeeTypes.add(new EmployeeType());
        employeeTypes.add(new EmployeeType());
        when(employeeTypeRepository.findAll()).thenReturn(employeeTypes);

        Model model = mock(Model.class);

        // When
        String result = employeeTypeController.parameters(model);

        // Then
        assertEquals("hr/employeeTypes", result);
        verify(model, times(1)).addAttribute(eq("employeeTypes"), eq(employeeTypes));
    }

@Test
@DisplayName("getById should return employeeType view with the specified employee type")
void getById_ShouldReturnEmployeeTypeViewWithSpecifiedEmployeeType() {
    // Given
    Integer id = 1;
    EmployeeType employeeType = new EmployeeType();
    when(employeeTypeService.findById(id)).thenReturn(Optional.of(employeeType));

    // When
    EmployeeType result = employeeTypeController.getById(id);

    // Then
    assertEquals(employeeType, result);
    verify(employeeTypeService, times(1)).findById(id);
}


    @Test
    @DisplayName("save should save the employee type and redirect to employeeTypes view")
    void save_ShouldSaveEmployeeTypeAndRedirectToEmployeeTypesView() {
        // Given
        EmployeeType employeeType = new EmployeeType();

        // When
        String result = employeeTypeController.save(employeeType);

        // Then
        assertEquals("redirect:/hr/employeeTypes", result);
        verify(employeeTypeService, times(1)).save(employeeType);
    }

    @Test
    @DisplayName("delete should delete the specified employee type and redirect to employeeTypes view")
    void delete_ShouldDeleteSpecifiedEmployeeTypeAndRedirectToEmployeeTypesView() {
        // Given
        int id = 1;

        // When
        String result = employeeTypeController.delete(id);

        // Then
        assertEquals("redirect:/hr/employeeTypes", result);
        verify(employeeTypeService, times(1)).delete(id);
    }
}
