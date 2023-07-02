package com.Castro.cmsApp.accounts.services;

import com.Castro.cmsApp.accounts.models.InvoiceStatus;
import com.Castro.cmsApp.accounts.repositories.InvoiceStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class InvoiceStatusServiceTests {

    private InvoiceStatusService invoiceStatusService;

    @Mock
    private InvoiceStatusRepository invoiceStatusRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        invoiceStatusService = new InvoiceStatusService(invoiceStatusRepository);
    }

    @Test
    @DisplayName("Test findAll() method")
    public void testFindAll() {
        // Given
        List<InvoiceStatus> invoiceStatuses = new ArrayList<>();
        invoiceStatuses.add(new InvoiceStatus());
        invoiceStatuses.add(new InvoiceStatus());

        when(invoiceStatusRepository.findAll()).thenReturn(invoiceStatuses);

        // When
        List<InvoiceStatus> result = invoiceStatusService.findAll();

        // Then
        assertEquals(invoiceStatuses, result);
    }

    @Test
    @DisplayName("Test findById() method")
    public void testFindById() {
        // Given
        InvoiceStatus invoiceStatus = new InvoiceStatus();
        Optional<InvoiceStatus> optionalInvoiceStatus = Optional.of(invoiceStatus);

        when(invoiceStatusRepository.findById(1)).thenReturn(optionalInvoiceStatus);

        // When
        Optional<InvoiceStatus> result = invoiceStatusService.findById(1);

        // Then
        assertEquals(optionalInvoiceStatus, result);
    }

    @Test
    @DisplayName("Test delete() method")
    public void testDelete() {
        // Given

        // When
        invoiceStatusService.delete(1);

        // Then
        verify(invoiceStatusRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Test save() method")
    public void testSave() {
        // Given
        InvoiceStatus invoiceStatus = new InvoiceStatus();

        // When
        invoiceStatusService.save(invoiceStatus);

        // Then
        verify(invoiceStatusRepository, times(1)).save(invoiceStatus);
    }
}
