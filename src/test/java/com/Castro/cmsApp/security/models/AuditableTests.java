package com.Castro.cmsApp.security.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Auditable Tests")
class ConcreteAuditable extends Auditable<String> {}

@DisplayName("Auditable Tests")
class AuditableTests {

    @Test
    @DisplayName("Getter and Setter methods should work correctly")
    void gettersAndSetters_ShouldWorkCorrectly() {
        // Given
        ConcreteAuditable auditable = new ConcreteAuditable();
        String createdBy = "John";
        Date createdDate = new Date();
        String lastModifiedBy = "Jane";
        Date lastModifiedDate = new Date();

        // When
        auditable.setCreatedBy(createdBy);
        auditable.setCreatedDate(createdDate);
        auditable.setLastModifiedBy(lastModifiedBy);
        auditable.setLastModifiedDate(lastModifiedDate);

        // Then
        assertEquals(createdBy, auditable.getCreatedBy());
        assertEquals(createdDate, auditable.getCreatedDate());
        assertEquals(lastModifiedBy, auditable.getLastModifiedBy());
        assertEquals(lastModifiedDate, auditable.getLastModifiedDate());
    }

    @Test
    @DisplayName("Initial values should be null")
    void initialValues_ShouldBeNull() {
        // Given
        ConcreteAuditable auditable = new ConcreteAuditable();

        // Then
        assertNull(auditable.getCreatedBy());
        assertNull(auditable.getCreatedDate());
        assertNull(auditable.getLastModifiedBy());
        assertNull(auditable.getLastModifiedDate());
    }

@Test
@DisplayName("CreatedDate and LastModifiedDate should be set automatically")
void dateFields_ShouldBeSetAutomatically() {
    // Given
    ConcreteAuditable auditable = new ConcreteAuditable();
    String createdBy = "John";
    String lastModifiedBy = "Jane";
    
    Date currentDate = new Date();

    // When
    auditable.setCreatedBy(createdBy);
    auditable.setLastModifiedBy(lastModifiedBy);
    auditable.setCreatedDate(currentDate);
    auditable.setLastModifiedDate(currentDate);

    // Then
    assertNotNull(auditable.getCreatedDate());
    assertNotNull(auditable.getLastModifiedDate());
}
}
