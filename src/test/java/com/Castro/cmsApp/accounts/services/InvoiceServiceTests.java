package com.Castro.cmsApp.accounts.services;

import com.Castro.cmsApp.accounts.models.Invoice;
import com.Castro.cmsApp.accounts.repositories.InvoiceRepository;
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

public class InvoiceServiceTests {

    private InvoiceService invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        invoiceService = new InvoiceService(invoiceRepository);
    }

    @Test
    @DisplayName("Test findAll() method")
    public void testFindAll() {
        // Given
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(new Invoice());
        invoices.add(new Invoice());

        when(invoiceRepository.findAll()).thenReturn(invoices);

        // When
        List<Invoice> result = invoiceService.findAll();

        // Then
        assertEquals(invoices, result);
    }

    @Test
    @DisplayName("Test findById() method")
    public void testFindById() {
        // Given
        Invoice invoice = new Invoice();
        Optional<Invoice> optionalInvoice = Optional.of(invoice);

        when(invoiceRepository.findById(1)).thenReturn(optionalInvoice);

        // When
        Invoice result = invoiceService.findById(1);

        // Then
        assertEquals(invoice, result);
    }

    @Test
    @DisplayName("Test delete() method")
    public void testDelete() {
        // Given

        // When
        invoiceService.delete(1);

        // Then
        verify(invoiceRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Test save() method")
    public void testSave() {
        // Given
        Invoice invoice = new Invoice();

        // When
        invoiceService.save(invoice);

        // Then
        verify(invoiceRepository, times(1)).save(invoice);
    }
}
