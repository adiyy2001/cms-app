package com.Castro.cmsApp.security.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Role Tests")
class RoleTests {

    @Test
    @DisplayName("Getter and Setter methods should work correctly")
    void gettersAndSetters_ShouldWorkCorrectly() {
        // Given
        Role role = new Role();
        Integer id = 1;
        String description = "ROLE_ADMIN";
        String details = "Administrator role";

        // When
        role.setId(id);
        role.setDescription(description);
        role.setDetails(details);

        // Then
        assertEquals(id, role.getId());
        assertEquals(description, role.getDescription());
        assertEquals(details, role.getDetails());
    }

    @Test
    @DisplayName("toString method should return a string representation of the object")
    void toString_ShouldReturnStringRepresentationOfObject() {
        // Given
        Role role = new Role(1, "ROLE_ADMIN", "Administrator role");
        String expected = "Role(id=1, description=ROLE_ADMIN, details=Administrator role)";

        // When
        String result = role.toString();

        // Then
        assertEquals(expected, result);
    }
}
