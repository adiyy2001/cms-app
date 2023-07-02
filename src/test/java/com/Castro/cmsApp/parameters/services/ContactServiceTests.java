package com.Castro.cmsApp.parameters.services;

import com.Castro.cmsApp.parameters.models.Contact;
import com.Castro.cmsApp.parameters.repositories.ContactRepository;

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

@DisplayName("ContactService Tests")
class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findAll should return all contacts")
    void findAll_ShouldReturnAllContacts() {
        // Given
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact());
        contacts.add(new Contact());

        when(contactRepository.findAll()).thenReturn(contacts);

        // When
        List<Contact> result = contactService.findAll();

        // Then
        assertEquals(contacts.size(), result.size());
        verify(contactRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findById with valid ID should return the corresponding contact")
    void findById_WithValidId_ShouldReturnContact() {
        // Given
        int id = 1;
        Contact contact = new Contact();
        when(contactRepository.findById(id)).thenReturn(Optional.of(contact));

        // When
        Contact result = contactService.findById(id);

        // Then
        assertEquals(contact, result);
        verify(contactRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("findById with invalid ID should return null")
    void findById_WithInvalidId_ShouldReturnNull() {
        // Given
        int id = 1;
        when(contactRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Contact result = contactService.findById(id);

        // Then
        assertEquals(null, result);
        verify(contactRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("delete should delete the contact")
    void delete_ShouldDeleteContact() {
        // Given
        int id = 1;

        // When
        contactService.delete(id);

        // Then
        verify(contactRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("save should save the contact")
    void save_ShouldSaveContact() {
        // Given
        Contact contact = new Contact();

        // When
        contactService.save(contact);

        // Then
        verify(contactRepository, times(1)).save(contact);
    }
}
