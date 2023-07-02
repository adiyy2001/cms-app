package com.Castro.cmsApp.parameters.services;

import com.Castro.cmsApp.parameters.models.Client;
import com.Castro.cmsApp.parameters.repositories.ClientRepository;

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

@DisplayName("ClientService Tests")
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findAll should return all clients")
    void findAll_ShouldReturnAllClients() {
        // Given
        List<Client> clients = new ArrayList<>();
        clients.add(new Client());
        clients.add(new Client());

        when(clientRepository.findAll()).thenReturn(clients);

        // When
        List<Client> result = clientService.findAll();

        // Then
        assertEquals(clients.size(), result.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findById with valid ID should return the corresponding client")
    void findById_WithValidId_ShouldReturnClient() {
        // Given
        int id = 1;
        Client client = new Client();
        when(clientRepository.findById(id)).thenReturn(Optional.of(client));

        // When
        Client result = clientService.findById(id);

        // Then
        assertEquals(client, result);
        verify(clientRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("findById with invalid ID should return null")
    void findById_WithInvalidId_ShouldReturnNull() {
        // Given
        int id = 1;
        when(clientRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Client result = clientService.findById(id);

        // Then
        assertEquals(null, result);
        verify(clientRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("deleteById should delete the client")
    void deleteById_ShouldDeleteClient() {
        // Given
        int id = 1;

        // When
        clientService.deleteById(id);

        // Then
        verify(clientRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("save should save the client")
    void save_ShouldSaveClient() {
        // Given
        Client client = new Client();

        // When
        clientService.save(client);

        // Then
        verify(clientRepository, times(1)).save(client);
    }
}
