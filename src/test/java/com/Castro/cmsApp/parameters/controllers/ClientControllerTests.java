package com.Castro.cmsApp.parameters.controllers;


import com.Castro.cmsApp.parameters.models.Client;
import com.Castro.cmsApp.parameters.services.ClientService;
import com.Castro.cmsApp.parameters.services.CountryService;
import com.Castro.cmsApp.parameters.services.StateService;
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

public class ClientControllerTests {

    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @Mock
    private CountryService countryService;

    @Mock
    private StateService stateService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        clientController = new ClientController(clientService, countryService, stateService);
    }

    @Test
    @DisplayName("Test findAll() method")
    public void testFindAll() {
        // Given
        List<Client> clients = new ArrayList<>();
        when(clientService.findAll()).thenReturn(clients);

        // When
        String viewName = clientController.findAll(model);

        // Then
        assertEquals("/parameters/clients", viewName);
        verify(clientService, times(1)).findAll();
        verify(model, times(1)).addAttribute("clients", clients);
        verify(model, times(1)).addAttribute("countries", countryService.findAll());
        verify(model, times(1)).addAttribute("states", stateService.findAll());
    }

    @Test
    @DisplayName("Test addClient() method")
    public void testAddClient() {
        // Given

        // When
        String viewName = clientController.addClient(model);

        // Then
        assertEquals("parameters/clientAdd", viewName);
        verify(model, times(1)).addAttribute("countries", countryService.findAll());
    }

    @Test
    @DisplayName("Test editClient() method with Edit operation")
    public void testEditClient_Edit() {
        // Given
        int id = 1;
        String op = "Edit";
        Client client = new Client();
        when(clientService.findById(id)).thenReturn(client);

        // When
        String viewName = clientController.editClient(id, op, model);

        // Then
        assertEquals("/parameters/clientEdit", viewName);
        verify(clientService, times(1)).findById(id);
        verify(model, times(1)).addAttribute("client", client);
        verify(model, times(1)).addAttribute("clients", clientService.findAll());
        verify(model, times(1)).addAttribute("countries", countryService.findAll());
        verify(model, times(1)).addAttribute("states", stateService.findAll());
    }

    @Test
    @DisplayName("Test editClient() method with Details operation")
    public void testEditClient_Details() {
        // Given
        int id = 1;
        String op = "Details";
        Client client = new Client();
        when(clientService.findById(id)).thenReturn(client);

        // When
        String viewName = clientController.editClient(id, op, model);

        // Then
        assertEquals("/parameters/clientDetails", viewName);
        verify(clientService, times(1)).findById(id);
        verify(model, times(1)).addAttribute("client", client);
        verify(model, times(1)).addAttribute("clients", clientService.findAll());
        verify(model, times(1)).addAttribute("countries", countryService.findAll());
        verify(model, times(1)).addAttribute("states", stateService.findAll());
    }

    @Test
    @DisplayName("Test save() method")
    public void testSave() {
        // Given
        Client client = new Client();

        // When
        String viewName = clientController.save(client);

        // Then
        assertEquals("redirect:/parameters/clients", viewName);
        verify(clientService, times(1)).save(client);
    }

    @Test
    @DisplayName("Test deleteById() method")
    public void testDeleteById() {
        // Given
        int id = 1;

        // When
        String viewName = clientController.deleteById(id);

        // Then
        assertEquals("redirect:/parameters/clients", viewName);
        verify(clientService, times(1)).deleteById(id);
    }
}
