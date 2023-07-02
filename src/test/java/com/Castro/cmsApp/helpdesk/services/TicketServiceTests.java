package com.Castro.cmsApp.helpdesk.services;

import com.Castro.cmsApp.helpdesk.models.Ticket;
import com.Castro.cmsApp.helpdesk.repositories.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TicketServiceTests {

    private TicketService ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ticketService = new TicketService(ticketRepository);
    }

    @Test
    @DisplayName("Test findAll() method")
    public void testFindAll() {
        // Given
        Ticket ticket1 = new Ticket(1, "Subject 1", "Details 1", null, null, null, null, null, null, null, null);
        Ticket ticket2 = new Ticket(2, "Subject 2", "Details 2", null, null, null, null, null, null, null, null);
        List<Ticket> tickets = Arrays.asList(ticket1, ticket2);

        when(ticketRepository.findAll()).thenReturn(tickets);

        // When
        List<Ticket> result = ticketService.findAll();

        // Then
        assertEquals(tickets, result);
    }

    @Test
    @DisplayName("Test findById() method")
    public void testFindById() {
        // Given
        Ticket ticket = new Ticket(1, "Subject", "Details", null, null, null, null, null, null, null, null);
        Optional<Ticket> optionalTicket = Optional.of(ticket);

        when(ticketRepository.findById(1)).thenReturn(optionalTicket);

        // When
        Optional<Ticket> result = ticketService.findById(1);

        // Then
        assertEquals(optionalTicket, result);
    }

    @Test
    @DisplayName("Test delete() method")
    public void testDelete() {
        // Given

        // When
        ticketService.delete(1);

        // Then
        verify(ticketRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Test save() method")
    public void testSave() {
        // Given
        Ticket ticket = new Ticket(1, "Subject", "Details", null, null, null, null, null, null, null, null);

        // When
        ticketService.save(ticket);

        // Then
        verify(ticketRepository, times(1)).save(ticket);
    }
}
