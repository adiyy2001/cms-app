package com.Castro.cmsApp.security.controllers;

import com.Castro.cmsApp.security.models.Role;
import com.Castro.cmsApp.security.services.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RoleControllerTest {

    @Mock
    private RoleService roleService;

    @Mock
    private Model model;

    private RoleController roleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roleController = new RoleController(roleService);
    }

    @Test
    void testParameters() {
        // Arrange
        List<Role> roles = new ArrayList<>();
        when(roleService.findAll()).thenReturn(roles);

        // Act
        String viewName = roleController.parameters(model);

        // Assert
        assertEquals("security/roles", viewName);
        verify(roleService, times(1)).findAll();
        verify(model, times(1)).addAttribute("roles", roles);
    }

    @Test
    void testGetById() {
        // Arrange
        Integer roleId = 1;
        Role expectedRole = new Role();
        when(roleService.findById(roleId)).thenReturn(expectedRole);

        // Act
        Role result = roleController.getById(roleId);

        // Assert
        assertEquals(expectedRole, result);
        verify(roleService, times(1)).findById(roleId);
    }

    @Test
    void testSave() {
        // Arrange
        Role role = new Role();

        // Act
        String viewName = roleController.save(role);

        // Assert
        assertEquals("redirect:/security/roles", viewName);
        verify(roleService, times(1)).save(role);
    }

    @Test
    void testDelete() {
        // Arrange
        Integer roleId = 1;

        // Act
        String viewName = roleController.delete(roleId);

        // Assert
        assertEquals("redirect:/security/roles", viewName);
        verify(roleService, times(1)).delete(roleId);
    }

    @Test
    void testAssignRole() {
        // Arrange
        Integer userId = 1;
        Integer roleId = 1;

        // Act
        String viewName = roleController.assignRole(userId, roleId);

        // Assert
        assertEquals("redirect:/security/user/Edit/" + userId, viewName);
        verify(roleService, times(1)).assignUserRole(userId, roleId);
    }

    @Test
    void testUnassignRole() {
        // Arrange
        Integer userId = 1;
        Integer roleId = 1;

        // Act
        String viewName = roleController.unassignRole(userId, roleId);

        // Assert
        assertEquals("redirect:/security/user/Edit/" + userId, viewName);
        verify(roleService, times(1)).unassignUserRole(userId, roleId);
    }
}
