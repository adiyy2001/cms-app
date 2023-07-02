package com.Castro.cmsApp.security.services;

import com.Castro.cmsApp.security.models.User;
import com.Castro.cmsApp.security.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder encoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnAllUsers() {
        // Arrange
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<User> result = userService.findAll();

        // Assert
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void findById_ExistingId_ShouldReturnUser() {
        // Arrange
        int userId = 1;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        User result = userService.findById(userId);

        // Assert
        assertEquals(user, result);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void findById_NonExistingId_ShouldReturnNull() {
        // Arrange
        int userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        User result = userService.findById(userId);

        // Assert
        assertEquals(null, result);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void delete_ShouldCallUserRepositoryDeleteById() {
        // Arrange
        int userId = 1;

        // Act
        userService.delete(userId);

        // Assert
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void save_ShouldEncodePasswordAndCallUserRepositorySave() {
        // Arrange
        User user = new User();
        String password = "password";
        String encodedPassword = "encodedPassword";
        user.setPassword(password);
        when(encoder.encode(password)).thenReturn(encodedPassword);
        when(userRepository.save(user)).thenReturn(user);

        // Act
        userService.save(user);

        // Assert
        verify(encoder, times(1)).encode(password);
        verify(userRepository, times(1)).save(user);
        assertEquals(encodedPassword, user.getPassword());
    }
}
