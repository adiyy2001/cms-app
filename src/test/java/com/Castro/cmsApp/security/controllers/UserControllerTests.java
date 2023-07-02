package com.Castro.cmsApp.security.controllers;

import com.Castro.cmsApp.security.models.User;
import com.Castro.cmsApp.security.services.RoleService;
import com.Castro.cmsApp.security.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTests {

    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private RoleService roleService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService, roleService);
    }

    @Test
    @DisplayName("Test getAll() method")
    public void testGetAll() {
        // Given

        // When
        String result = userController.getAll(model);

        // Then
        assertEquals("/security/users", result);
        verify(model, times(1)).addAttribute("users", userService.findAll());
    }

    @Test
    @DisplayName("Test editUser() method")
    public void testEditUser() {
        // Given
        Integer id = 1;
        String op = "edit";
        User user = new User();
        
        when(userService.findById(id)).thenReturn(user);
        when(roleService.getUserRoles(user)).thenReturn(null);
        when(roleService.getUserNotRoles(user)).thenReturn(null);

        // When
        String result = userController.editUser(id, op, model);

        // Then
        assertEquals("/security/useredit", result);
        verify(model, times(1)).addAttribute("user", user);
        verify(model, times(1)).addAttribute("userRoles", null);
        verify(model, times(1)).addAttribute("userNotRoles", null);
    }

@Test
@DisplayName("Test addNew() method")
public void testAddNew() {
    // Given
    User user = new User();
    RedirectView expectedRedirectView = new RedirectView("/login", true);

    // When
    RedirectView resultRedirectView = userController.addNew(user, redirectAttributes);

    // Then
    assertEquals(expectedRedirectView.getUrl(), resultRedirectView.getUrl());
    verify(userService, times(1)).save(user);
    verify(redirectAttributes, times(1)).addFlashAttribute("message", "You have successfully registered a new user!");
}

}
