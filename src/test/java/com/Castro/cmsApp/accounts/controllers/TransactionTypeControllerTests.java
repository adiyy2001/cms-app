package com.Castro.cmsApp.accounts.controllers;

import com.Castro.cmsApp.accounts.models.TransactionType;
import com.Castro.cmsApp.accounts.services.TransactionTypeService;
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

@DisplayName("TransactionTypeController Tests")
class TransactionTypeControllerTest {

    @Mock
    private TransactionTypeService transactionTypeService;

    @InjectMocks
    private TransactionTypeController transactionTypeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("parameters should return transactionTypes view with all transaction types")
    void parameters_ShouldReturnTransactionTypesViewWithAllTransactionTypes() {
        // Given
        List<TransactionType> transactionTypes = new ArrayList<>();
        transactionTypes.add(new TransactionType());
        transactionTypes.add(new TransactionType());
        when(transactionTypeService.findAll()).thenReturn(transactionTypes);

        Model model = mock(Model.class);

        // When
        String result = transactionTypeController.parameters(model);

        // Then
        assertEquals("accounts/transactionTypes", result);
        verify(model, times(1)).addAttribute(eq("transactionTypes"), eq(transactionTypes));
    }

    @Test
    @DisplayName("getById should return TransactionType as JSON response")
    void getById_ShouldReturnTransactionTypeAsJsonResponse() {
        // Given
        int id = 1;
        TransactionType transactionType = new TransactionType();
        when(transactionTypeService.findById(id)).thenReturn(Optional.of(transactionType));

        // When
        TransactionType result = transactionTypeController.getById(id);

        // Then
        assertEquals(transactionType, result);
    }

    @Test
    @DisplayName("addNew should save the transaction type and redirect to transactionTypes view")
    void addNew_ShouldSaveTransactionTypeAndRedirectToTransactionTypesView() {
        // Given
        TransactionType transactionType = new TransactionType();

        // When
        String result = transactionTypeController.addNew(transactionType);

        // Then
        assertEquals("redirect:/accounts/transactionTypes", result);
        verify(transactionTypeService, times(1)).save(transactionType);
    }

    @Test
    @DisplayName("delete should delete the transaction type and redirect to transactionTypes view")
    void delete_ShouldDeleteTransactionTypeAndRedirectToTransactionTypesView() {
        // Given
        int id = 1;

        // When
        String result = transactionTypeController.delete(id);

        // Then
        assertEquals("redirect:/accounts/transactionTypes", result);
        verify(transactionTypeService, times(1)).delete(id);
    }
}
