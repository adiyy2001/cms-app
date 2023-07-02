package com.Castro.cmsApp.parameters.controllers;

import com.Castro.cmsApp.parameters.models.Contact;
import com.Castro.cmsApp.parameters.services.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ContactControllerTests {

    private ContactController contactController;

    @Mock
    private ContactService contactService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        contactController = new ContactController(contactService);
    }

    @Test
    @DisplayName("Test getAll() method")
    public void testGetAll() {
        // Given
        List<Contact> contacts = new ArrayList<>();
        when(contactService.findAll()).thenReturn(contacts);

        // When
        String viewName = contactController.getAll(model);

        // Then
        assertEquals("/parameters/contacts", viewName);
        verify(contactService, times(1)).findAll();
        verify(model, times(1)).addAttribute("contacts", contacts);
    }

    @Test
    @DisplayName("Test getContact() method")
    public void testGetContact() {
        // Given
        int id = 1;
        Contact contact = new Contact();
        when(contactService.findById(id)).thenReturn(contact);

        // When
        Contact result = contactController.getContact(id);

        // Then
        assertEquals(contact, result);
        verify(contactService, times(1)).findById(id);
    }

    @Test
    @DisplayName("Test addContact() method")
    public void testAddContact() {
        // Given

        // When
        String viewName = contactController.addContact();

        // Then
        assertEquals("parameters/contactAdd", viewName);
    }

    @Test
    @DisplayName("Test editContact() method with Edit operation")
    public void testEditContact_Edit() {
        // Given
        int id = 1;
        String op = "Edit";
        Contact contact = new Contact();
        when(contactService.findById(id)).thenReturn(contact);

        // When
        String viewName = contactController.editContact(id, op, model);

        // Then
        assertEquals("/parameters/contactEdit", viewName);
        verify(contactService, times(1)).findById(id);
        verify(model, times(1)).addAttribute("contact", contact);
    }

    @Test
    @DisplayName("Test editContact() method with Details operation")
    public void testEditContact_Details() {
        // Given
        int id = 1;
        String op = "Details";
        Contact contact = new Contact();
        when(contactService.findById(id)).thenReturn(contact);

        // When
        String viewName = contactController.editContact(id, op, model);

        // Then
        assertEquals("/parameters/contactDetails", viewName);
        verify(contactService, times(1)).findById(id);
        verify(model, times(1)).addAttribute("contact", contact);
    }

    @Test
    @DisplayName("Test save() method")
    public void testSave() {
        // Given
        Contact contact = new Contact();

        // When
        String viewName = contactController.save(contact);

        // Then
        assertEquals("redirect:/parameters/contacts", viewName);
        verify(contactService, times(1)).save(contact);
    }

    @Test
    @DisplayName("Test delete() method")
    public void testDelete() {
        // Given
        int id = 1;

        // When
        String viewName = contactController.delete(id);

        // Then
        assertEquals("redirect:/parameters/contacts", viewName);
        verify(contactService, times(1)).delete(id);
    }
}
