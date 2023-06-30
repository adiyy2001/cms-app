package com.Castro.cmsApp.hr.services;

import com.Castro.cmsApp.hr.models.Employee;
import com.Castro.cmsApp.hr.repositories.EmployeeRepository;
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

public class EmployeeServiceTests {

    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    @DisplayName("Test findAll() method")
    public void testFindAll() {
        // Given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());

        when(employeeRepository.findAll()).thenReturn(employees);

        // When
        List<Employee> result = employeeService.findAll();

        // Then
        assertEquals(employees, result);
    }

    @Test
    @DisplayName("Test findById() method")
    public void testFindById() {
        // Given
        Employee employee = new Employee();
        Optional<Employee> optionalEmployee = Optional.of(employee);

        when(employeeRepository.findById(1)).thenReturn(optionalEmployee);

        // When
        Employee result = employeeService.findById(1);

        // Then
        assertEquals(employee, result);
    }

    @Test
    @DisplayName("Test delete() method")
    public void testDelete() {
        // Given

        // When
        employeeService.delete(1);

        // Then
        verify(employeeRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Test save() method")
    public void testSave() {
        // Given
        Employee employee = new Employee();

        // When
        employeeService.save(employee);

        // Then
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    @DisplayName("Test findByUsername() method")
    public void testFindByUsername() {
        // Given
        Employee employee = new Employee();

        when(employeeRepository.findByUsername("username")).thenReturn(employee);

        // When
        Employee result = employeeService.findByUsername("username");

        // Then
        assertEquals(employee, result);
    }

    @Test
    @DisplayName("Test findByKeyword() method")
    public void testFindByKeyword() {
        // Given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());

        when(employeeRepository.findByKeyword("keyword")).thenReturn(employees);

        // When
        List<Employee> result = employeeService.findByKeyword("keyword");

        // Then
        assertEquals(employees, result);
    }
}
