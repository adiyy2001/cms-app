package com.Castro.cmsApp.parameters.services;

import com.Castro.cmsApp.parameters.models.Department;
import com.Castro.cmsApp.parameters.repositories.DepartmentRepository;

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

@DisplayName("DepartmentService Tests")
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findAll should return all departments")
    void findAll_ShouldReturnAllDepartments() {
        // Given
        List<Department> departments = new ArrayList<>();
        departments.add(new Department());
        departments.add(new Department());

        when(departmentRepository.findAll()).thenReturn(departments);

        // When
        List<Department> result = departmentService.findAll();

        // Then
        assertEquals(departments.size(), result.size());
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findById with valid ID should return the corresponding department")
    void findById_WithValidId_ShouldReturnDepartment() {
        // Given
        int id = 1;
        Department department = new Department();
        when(departmentRepository.findById(id)).thenReturn(Optional.of(department));

        // When
        Department result = departmentService.findById(id);

        // Then
        assertEquals(department, result);
        verify(departmentRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("findById with invalid ID should return null")
    void findById_WithInvalidId_ShouldReturnNull() {
        // Given
        int id = 1;
        when(departmentRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Department result = departmentService.findById(id);

        // Then
        assertEquals(null, result);
        verify(departmentRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("delete should delete the department")
    void delete_ShouldDeleteDepartment() {
        // Given
        int id = 1;

        // When
        departmentService.delete(id);

        // Then
        verify(departmentRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("save should save the department")
    void save_ShouldSaveDepartment() {
        // Given
        Department department = new Department();

        // When
        departmentService.save(department);

        // Then
        verify(departmentRepository, times(1)).save(department);
    }
}
