package com.Castro.cmsApp.helpdesk.services;

import com.Castro.cmsApp.helpdesk.models.TicketStatus;
import com.Castro.cmsApp.helpdesk.repositories.TicketStatusRepository;
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

public class TicketStatusServiceTests {

    private TicketStatusService ticketStatusService;

    @Mock
    private TicketStatusRepository ticketStatusRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ticketStatusService = new TicketStatusService(ticketStatusRepository);
    }

    @Test
    @DisplayName("Test findAll() method")
    public void testFindAll() {
        // Given
        TicketStatus ticketStatus1 = new TicketStatus();
        TicketStatus ticketStatus2 = new TicketStatus();
        List<TicketStatus> ticketStatuses = Arrays.asList(ticketStatus1, ticketStatus2);

        when(ticketStatusRepository.findAll()).thenReturn(ticketStatuses);

        // When
        List<TicketStatus> result = ticketStatusService.findAll();

        // Then
        assertEquals(ticketStatuses, result);
    }

    @Test
    @DisplayName("Test findById() method")
    public void testFindById() {
        // Given
        TicketStatus ticketStatus = new TicketStatus();
        Optional<TicketStatus> optionalTicketStatus = Optional.of(ticketStatus);

        when(ticketStatusRepository.findById(1)).thenReturn(optionalTicketStatus);

        // When
        Optional<TicketStatus> result = ticketStatusService.findById(1);

        // Then
        assertEquals(optionalTicketStatus, result);
    }

    @Test
    @DisplayName("Test delete() method")
    public void testDelete() {
        // Given

        // When
        ticketStatusService.delete(1);

        // Then
        verify(ticketStatusRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Test save() method")
    public void testSave() {
        // Given
        TicketStatus ticketStatus = new TicketStatus();

        // When
        ticketStatusService.save(ticketStatus);

        // Then
        verify(ticketStatusRepository, times(1)).save(ticketStatus);
    }
}
