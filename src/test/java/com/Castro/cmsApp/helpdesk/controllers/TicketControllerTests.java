package com.Castro.cmsApp.helpdesk.controllers;

import com.Castro.cmsApp.helpdesk.models.Ticket;
import com.Castro.cmsApp.helpdesk.services.TicketService;
import com.Castro.cmsApp.helpdesk.services.TicketStatusService;
import com.Castro.cmsApp.parameters.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TicketControllerTests {

    private TicketController ticketController;

    @Mock
    private TicketService ticketService;

    @Mock
    private TicketStatusService ticketStatusService;

    @Mock
    private ClientService clientService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ticketController = new TicketController(ticketService, ticketStatusService, clientService);
    }

    @Test
    @DisplayName("Test findAll() method")
    public void testFindAll() {
        // Given

        // When
        String viewName = ticketController.findAll(model);

        // Then
        assertEquals("/helpdesk/tickets", viewName);
        verify(model, times(1)).addAttribute("tickets", ticketService.findAll());
        verify(model, times(1)).addAttribute("clients", clientService.findAll());
        verify(model, times(1)).addAttribute("ticketStatuses", ticketStatusService.findAll());
    }

    @Test
    @DisplayName("Test findById() method")
    public void testFindById() {
        // Given
        Ticket ticket = new Ticket();
        Optional<Ticket> optionalTicket = Optional.of(ticket);

        when(ticketService.findById(1)).thenReturn(optionalTicket);

        // When
        Optional<Ticket> result = ticketController.findById(1);

        // Then
        assertEquals(optionalTicket, result);
    }

    @Test
    @DisplayName("Test addNew() method")
    public void testAddNew() {
        // Given
        Ticket ticket = new Ticket();

        // When
        String viewName = ticketController.addNew(ticket);

        // Then
        assertEquals("redirect:/ticketList", viewName);
        verify(ticketService, times(1)).save(ticket);
    }

    @Test
    @DisplayName("Test update() method")
    public void testUpdate() {
        // Given
        Ticket ticket = new Ticket();

        // When
        String viewName = ticketController.update(ticket);

        // Then
        assertEquals("redirect:/ticketList", viewName);
        verify(ticketService, times(1)).save(ticket);
    }

    @Test
    @DisplayName("Test delete() method")
    public void testDelete() {
        // Given

        // When
        String viewName = ticketController.delete(1);

        // Then
        assertEquals("redirect:/ticketList", viewName);
        verify(ticketService, times(1)).delete(1);
    }
}
