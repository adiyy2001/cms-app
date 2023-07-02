package com.Castro.cmsApp.parameters.controllers;

import com.Castro.cmsApp.hr.models.Employee;
import com.Castro.cmsApp.hr.services.EmployeeService;
import com.Castro.cmsApp.parameters.models.Department;
import com.Castro.cmsApp.parameters.services.DepartmentService;
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

public class DepartmentControllerTests {

    private DepartmentController departmentController;

    @Mock
    private DepartmentService departmentService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        departmentController = new DepartmentController(departmentService, employeeService);
    }

    @Test
    @DisplayName("Test findAll() method")
    public void testFindAll() {
        // Given
        List<Department> departments = new ArrayList<>();
        when(departmentService.findAll()).thenReturn(departments);
        List<Employee> employees = new ArrayList<>();
        when(employeeService.findAll()).thenReturn(employees);

        // When
        String viewName = departmentController.findAll(model);

        // Then
        assertEquals("/parameters/departments", viewName);
        verify(departmentService, times(1)).findAll();
        verify(employeeService, times(1)).findAll();
        verify(model, times(1)).addAttribute("departments", departments);
        verify(model, times(1)).addAttribute("employees", employees);
    }

    @Test
    @DisplayName("Test findById() method")
    public void testFindById() {
        // Given
        Integer id = 1;
        Department department = new Department();
        when(departmentService.findById(id)).thenReturn(department);

        // When
        Department result = departmentController.findById(id);

        // Then
        assertEquals(department, result);
        verify(departmentService, times(1)).findById(id);
    }

    @Test
    @DisplayName("Test addNew() method")
    public void testAddNew() {
        // Given
        Department department = new Department();

        // When
        String viewName = departmentController.addNew(department);

        // Then
        assertEquals("redirect:/parameters/departments", viewName);
        verify(departmentService, times(1)).save(department);
    }

    @Test
    @DisplayName("Test update() method")
    public void testUpdate() {
        // Given
        Department department = new Department();

        // When
        String viewName = departmentController.update(department);

        // Then
        assertEquals("redirect:/parameters/departments", viewName);
        verify(departmentService, times(1)).save(department);
    }

    @Test
    @DisplayName("Test delete() method")
    public void testDelete() {
        // Given
        Integer id = 1;

        // When
        String viewName = departmentController.delete(id);

        // Then
        assertEquals("redirect:/parameters/departments", viewName);
        verify(departmentService, times(1)).delete(id);
    }
}
