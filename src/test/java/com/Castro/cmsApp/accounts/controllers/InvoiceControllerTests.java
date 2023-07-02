package com.Castro.cmsApp.accounts.controllers;

import com.Castro.cmsApp.accounts.models.Invoice;
import com.Castro.cmsApp.accounts.services.InvoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("InvoiceController Tests")
class InvoiceControllerTest {

    @Mock
    private InvoiceService invoiceService;

    @InjectMocks
    private InvoiceController invoiceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getAll should return invoices view with all invoices")
    void getAll_ShouldReturnInvoicesViewWithAllInvoices() {
        // Given
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(new Invoice());
        invoices.add(new Invoice());
        when(invoiceService.findAll()).thenReturn(invoices);

        Model model = mock(Model.class);

        // When
        String result = invoiceController.getAll(model);

        // Then
        assertEquals("/accounts/invoices", result);
        verify(model, times(1)).addAttribute(eq("invoices"), eq(invoices));
    }

    @Test
    @DisplayName("addInvoice should return invoiceAdd view")
    void addInvoice_ShouldReturnInvoiceAddView() {
        // When
        String result = invoiceController.addInvoice();

        // Then
        assertEquals("accounts/invoiceAdd", result);
    }

    @Test
    @DisplayName("editInvoice should return expected view based on op parameter")
    void editInvoice_ShouldReturnExpectedViewBasedOnOpParameter() {
        // Given
        int id = 1;
        String op = "Edit";
        Invoice invoice = new Invoice();
        when(invoiceService.findById(id)).thenReturn(invoice);

        Model model = mock(Model.class);

        // When
        String result = invoiceController.editInvoice(id, op, model);

        // Then
        assertEquals("/accounts/invoiceEdit", result);
        verify(model, times(1)).addAttribute(eq("invoice"), eq(invoice));
    }

    @Test
    @DisplayName("save should save the invoice and redirect to invoices view")
    void save_ShouldSaveInvoiceAndRedirectToInvoicesView() {
        // Given
        Invoice invoice = new Invoice();

        // When
        String result = invoiceController.save(invoice);

        // Then
        assertEquals("redirect:/accounts/invoices", result);
        verify(invoiceService, times(1)).save(invoice);
    }

    @Test
    @DisplayName("delete should delete the invoice and redirect to invoices view")
    void delete_ShouldDeleteInvoiceAndRedirectToInvoicesView() {
        // Given
        int id = 1;

        // When
        String result = invoiceController.delete(id);

        // Then
        assertEquals("redirect:/accounts/invoices", result);
        verify(invoiceService, times(1)).delete(id);
    }
}

