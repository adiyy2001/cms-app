package com.Castro.cmsApp.security.services;

import com.Castro.cmsApp.security.models.Role;
import com.Castro.cmsApp.security.models.User;
import com.Castro.cmsApp.security.repositories.RoleRepository;
import com.Castro.cmsApp.security.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RoleService roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnAllRoles() {
        // Arrange
        List<Role> roles = new ArrayList<>();
        roles.add(new Role());
        roles.add(new Role());
        when(roleRepository.findAll()).thenReturn(roles);

        // Act
        List<Role> result = roleService.findAll();

        // Assert
        assertEquals(2, result.size());
        verify(roleRepository, times(1)).findAll();
    }

    @Test
    void findById_ExistingId_ShouldReturnRole() {
        // Arrange
        int roleId = 1;
        Role role = new Role();
        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        // Act
        Role result = roleService.findById(roleId);

        // Assert
        assertEquals(role, result);
        verify(roleRepository, times(1)).findById(roleId);
    }

    @Test
    void findById_NonExistingId_ShouldReturnNull() {
        // Arrange
        int roleId = 1;
        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        // Act
        Role result = roleService.findById(roleId);

        // Assert
        assertEquals(null, result);
        verify(roleRepository, times(1)).findById(roleId);
    }

    @Test
    void delete_ShouldCallRoleRepositoryDeleteById() {
        // Arrange
        int roleId = 1;

        // Act
        roleService.delete(roleId);

        // Assert
        verify(roleRepository, times(1)).deleteById(roleId);
    }

    @Test
    void save_ShouldCallRoleRepositorySave() {
        // Arrange
        Role role = new Role();

        // Act
        roleService.save(role);

        // Assert
        verify(roleRepository, times(1)).save(role);
    }

    @Test
    void assignUserRole_ShouldAssignRoleToUser() {
        // Arrange
        int userId = 1;
        int roleId = 2;
        User user = new User();
        user.setId(userId);
        Role role = new Role();
        role.setId(roleId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));
        when(userRepository.save(user)).thenReturn(user);

        // Act
        roleService.assignUserRole(userId, roleId);

        // Assert
        verify(userRepository, times(1)).findById(userId);
        verify(roleRepository, times(1)).findById(roleId);
        assertEquals(1, user.getRoles().size());
        assertEquals(role, user.getRoles().iterator().next());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void unassignUserRole_ShouldRemoveRoleFromUser() {
        // Arrange
        int userId = 1;
        int roleId = 2;
        User user = new User();
        user.setId(userId);
        Role role = new Role();
        role.setId(roleId);
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRoles(userRoles);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        // Act
        roleService.unassignUserRole(userId, roleId);

        // Assert
        verify(userRepository, times(1)).findById(userId);
        user.getRoles().removeIf(x -> x.getId() == roleId);
        assertEquals(0, user.getRoles().size());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void unassignUserRole_ShouldRemoveRoleFromUserWhenMultipleRolesPresent() {
        // Arrange
        int userId = 1;
        int roleId = 2;
        User user = new User();
        user.setId(userId);
        Role role1 = new Role();
        role1.setId(1);
        Role role2 = new Role();
        role2.setId(roleId);
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role1);
        userRoles.add(role2);
        user.setRoles(userRoles);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        // Act
        roleService.unassignUserRole(userId, roleId);

        // Assert
        verify(userRepository, times(1)).findById(userId);
        user.getRoles().removeIf(x -> x.getId() == roleId);
        assertEquals(1, user.getRoles().size());
        assertEquals(role1, user.getRoles().iterator().next());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getUserRoles_ShouldReturnUserRoles() {
        // Arrange
        User user = new User();
        Set<Role> roles = new HashSet<>();
        Role role1 = new Role();
        role1.setId(1);
        Role role2 = new Role();
        role2.setId(2);
        roles.add(role1);
        roles.add(role2);
        user.setRoles(roles);

        // Act
        Set<Role> result = roleService.getUserRoles(user);

        // Assert
        assertEquals(2, result.size());
        assertEquals(roles, result);
    }
    

    @Test
    void getUserNotRoles_ShouldReturnUserNotAssignedRoles() {
        // Arrange
        int userId = 1;
        User user = new User();
        user.setId(userId);
        Set<Role> roles = new HashSet<>();
        Role role1 = new Role();
        role1.setId(1);
        Role role2 = new Role();
        role2.setId(2);
        roles.add(role1);
        user.setRoles(roles);
        List<Role> notAssignedRoles = new ArrayList<>();
        notAssignedRoles.add(role2);
        when(roleRepository.getUserNotRoles(userId)).thenReturn(notAssignedRoles);

        // Act
        List<Role> result = roleService.getUserNotRoles(user);

        // Assert
        assertEquals(1, result.size());
        assertEquals(role2, result.get(0));
        verify(roleRepository, times(1)).getUserNotRoles(userId);
    }
}
