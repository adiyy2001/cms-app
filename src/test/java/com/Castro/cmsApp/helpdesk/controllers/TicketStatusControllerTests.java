package com.Castro.cmsApp.helpdesk.controllers;

import com.Castro.cmsApp.helpdesk.models.TicketStatus;
import com.Castro.cmsApp.helpdesk.services.TicketStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TicketStatusControllerTests {

    private TicketStatusController ticketStatusController;

    @Mock
    private TicketStatusService ticketStatusService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ticketStatusController = new TicketStatusController(ticketStatusService);
    }

    @Test
    @DisplayName("Test findAll() method")
    public void testFindAll() {
        // Given

        // When
        String viewName = ticketStatusController.findAll(model);

        // Then
        assertEquals("/helpdesk/ticketStatuses", viewName);
        verify(model, times(1)).addAttribute("ticketStatuses", ticketStatusService.findAll());
    }

    @Test
    @DisplayName("Test findById() method")
    public void testFindById() {
        // Given
        TicketStatus ticketStatus = new TicketStatus();
        Optional<TicketStatus> optionalTicketStatus = Optional.of(ticketStatus);

        when(ticketStatusService.findById(1)).thenReturn(optionalTicketStatus);

        // When
        Optional<TicketStatus> result = ticketStatusController.findById(1);

        // Then
        assertEquals(optionalTicketStatus, result);
    }

    @Test
    @DisplayName("Test addNew() method")
    public void testAddNew() {
        // Given
        TicketStatus ticketStatus = new TicketStatus();

        // When
        String viewName = ticketStatusController.addNew(ticketStatus);

        // Then
        assertEquals("redirect:/helpdesk/ticketStatuses", viewName);
        verify(ticketStatusService, times(1)).save(ticketStatus);
    }

    @Test
    @DisplayName("Test delete() method")
    public void testDelete() {
        // Given

        // When
        String viewName = ticketStatusController.delete(1);

        // Then
        assertEquals("redirect:/helpdesk/ticketStatuses", viewName);
        verify(ticketStatusService, times(1)).delete(1);
    }
}
