package com.Castro.cmsApp.accounts.controllers;

import com.Castro.cmsApp.accounts.models.InvoiceStatus;
import com.Castro.cmsApp.accounts.services.InvoiceStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("InvoiceStatusController Tests")
class InvoiceStatusControllerTest {

    @Mock
    private InvoiceStatusService invoiceStatusService;

    @InjectMocks
    private InvoiceStatusController invoiceStatusController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findAll should return invoiceStatuses view with all invoiceStatuses")
    void findAll_ShouldReturnInvoiceStatusesViewWithAllInvoiceStatuses() {
        // Given
        List<InvoiceStatus> invoiceStatuses = new ArrayList<>();
        invoiceStatuses.add(new InvoiceStatus());
        invoiceStatuses.add(new InvoiceStatus());
        when(invoiceStatusService.findAll()).thenReturn(invoiceStatuses);

        Model model = mock(Model.class);

        // When
        String result = invoiceStatusController.findAll(model);

        // Then
        assertEquals("/accounts/invoiceStatuses", result);
        verify(model, times(1)).addAttribute(eq("invoiceStatuses"), eq(invoiceStatuses));
    }

    @Test
    @DisplayName("findById should return the expected InvoiceStatus")
    void findById_ShouldReturnExpectedInvoiceStatus() {
        // Given
        int id = 1;
        InvoiceStatus invoiceStatus = new InvoiceStatus();
        when(invoiceStatusService.findById(id)).thenReturn(Optional.of(invoiceStatus));

        // When
        Optional<InvoiceStatus> result = invoiceStatusController.findById(id);

        // Then
        assertEquals(Optional.of(invoiceStatus), result);
    }

    @Test
    @DisplayName("addNew should save the InvoiceStatus and redirect to invoiceStatuses view")
    void addNew_ShouldSaveInvoiceStatusAndRedirectToInvoiceStatusesView() {
        // Given
        InvoiceStatus invoiceStatus = new InvoiceStatus();

        // When
        String result = invoiceStatusController.addNew(invoiceStatus);

        // Then
        assertEquals("redirect:/accounts/invoiceStatuses", result);
        verify(invoiceStatusService, times(1)).save(invoiceStatus);
    }

    @Test
    @DisplayName("delete should delete the InvoiceStatus and redirect to invoiceStatuses view")
    void delete_ShouldDeleteInvoiceStatusAndRedirectToInvoiceStatusesView() {
        // Given
        int id = 1;

        // When
        String result = invoiceStatusController.delete(id);

        // Then
        assertEquals("redirect:/invoiceStatuses", result);
        verify(invoiceStatusService, times(1)).delete(id);
    }
}
