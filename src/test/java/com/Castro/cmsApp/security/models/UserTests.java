package com.Castro.cmsApp.security.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("User Tests")
class UserTests {

    @Test
    @DisplayName("User constructor should set the fields correctly")
    void userConstructor_ShouldSetFieldsCorrectly() {
        // Given
        Integer id = 1;
        String firstname = "John";
        String lastname = "Doe";
        String username = "johndoe";
        String password = "password";
        Set<Role> roles = new HashSet<>();

        // When
        User user = new User(id, firstname, lastname, username, password, roles);

        // Then
        assertEquals(id, user.getId());
        assertEquals(firstname, user.getFirstname());
        assertEquals(lastname, user.getLastname());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(roles, user.getRoles());
    }

    @Test
    @DisplayName("Getter and Setter methods should work correctly")
    void gettersAndSetters_ShouldWorkCorrectly() {
        // Given
        User user = new User();
        Integer id = 1;
        String firstname = "John";
        String lastname = "Doe";
        String username = "johndoe";
        String password = "password";
        Set<Role> roles = new HashSet<>();

        // When
        user.setId(id);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(roles);

        // Then
        assertEquals(id, user.getId());
        assertEquals(firstname, user.getFirstname());
        assertEquals(lastname, user.getLastname());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(roles, user.getRoles());
    }

    @Test
    @DisplayName("equals method should return true for the same object")
    void equals_ShouldReturnTrueForSameObject() {
        // Given
        User user = new User();

        // When/Then
        assertTrue(user.equals(user));
    }

    @Test
    @DisplayName("equals method should return true for equal objects")
    void equals_ShouldReturnTrueForEqualObjects() {
        // Given
        User user1 = new User(1, "John", "Doe", "johndoe", "password", new HashSet<>());
        User user2 = new User(1, "John", "Doe", "johndoe", "password", new HashSet<>());

        // When/Then
        assertTrue(user1.equals(user2));
    }

    @Test
    @DisplayName("equals method should return false for different objects")
    void equals_ShouldReturnFalseForDifferentObjects() {
        // Given
        User user1 = new User(1, "John", "Doe", "johndoe", "password", new HashSet<>());
        User user2 = new User(2, "Jane", "Smith", "janesmith", "password", new HashSet<>());

        // When/Then
        assertFalse(user1.equals(user2));
    }

    @Test
    @DisplayName("equals method should return false when comparing with null")
    void equals_ShouldReturnFalseWhenComparingWithNull() {
        // Given
        User user = new User(1, "John", "Doe", "johndoe", "password", new HashSet<>());

        // When/Then
        assertFalse(user.equals(null));
    }

    @Test
    @DisplayName("equals method should return false when comparing with a different class")
    void equals_ShouldReturnFalseWhenComparingWithDifferentClass() {
        // Given
        User user = new User(1, "John", "Doe", "johndoe", "password", new HashSet<>());
        Object obj = new Object();

        // When/Then
        assertFalse(user.equals(obj));
    }

    @Test
    @DisplayName("hashCode method should return the same hash code for equal objects")
    void hashCode_ShouldReturnSameHashCodeForEqualObjects() {
        // Given
        User user1 = new User(1, "John", "Doe", "johndoe", "password", new HashSet<>());
        User user2 = new User(1, "John", "Doe", "johndoe", "password", new HashSet<>());

        // When/Then
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    @DisplayName("toString method should return a string representation of the object")
    void toString_ShouldReturnStringRepresentationOfObject() {
        // Given
        User user = new User(1, "John", "Doe", "johndoe", "password", new HashSet<>());
        String expected = "User(Id=1, firstname=John, lastname=Doe, username=johndoe, password=password, roles=[])";

        // When
        String result = user.toString();

        // Then
        assertEquals(expected, result);
    }
}
