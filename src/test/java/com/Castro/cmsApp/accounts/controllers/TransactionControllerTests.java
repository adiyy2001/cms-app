package com.Castro.cmsApp.accounts.controllers;

import com.Castro.cmsApp.accounts.models.Transaction;
import com.Castro.cmsApp.accounts.services.TransactionService;
import com.Castro.cmsApp.accounts.services.TransactionStatusService;
import com.Castro.cmsApp.accounts.services.TransactionTypeService;
import com.Castro.cmsApp.parameters.services.ClientService;
import com.Castro.cmsApp.parameters.services.ContactService;
import com.Castro.cmsApp.parameters.services.SupplierService;
import com.Castro.cmsApp.hr.services.EmployeeService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@DisplayName("TransactionController Tests")
class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @Mock
    private TransactionStatusService transactionStatusService;

    @Mock
    private TransactionTypeService transactionTypeService;

    @Mock
    private ContactService contactService;

    @Mock
    private SupplierService supplierService;

    @Mock
    private ClientService clientService;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getAll should return transactions view with transactions list")
    void getAll_ShouldReturnTransactionsViewWithTransactionsList() {
        // Given
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction());
        transactions.add(new Transaction());
        when(transactionService.findAll()).thenReturn(transactions);

        Model model = mock(Model.class);

        // When
        String result = transactionController.getAll(model);

        // Then
        assertEquals("/accounts/transactions", result);
        verify(model, times(1)).addAttribute(eq("transactions"), eq(transactions));
    }

    @Test
    @DisplayName("addTransaction should return transactionAdd view")
    void addTransaction_ShouldReturnTransactionAddView() {
        // Given
        Model model = mock(Model.class);

        // When
        String result = transactionController.addTransaction(model);

        // Then
        assertEquals("accounts/transactionAdd", result);
    }

    @Test
    @DisplayName("editTransaction should return transaction Edit view")
    void editTransaction_ShouldReturnTransactionEditView() {
        // Given
        int id = 1;
        String op = "Edit";
        Transaction transaction = new Transaction();
        when(transactionService.findById(id)).thenReturn(transaction);

        Model model = mock(Model.class);

        // When
        String result = transactionController.editTransaction(id, op, model);

        // Then
        assertEquals("/accounts/transactionEdit", result);
        verify(transactionService, times(1)).findById(id);
    }

    @Test
    @DisplayName("editTransaction should return transaction Details view")
    void editTransaction_ShouldReturnTransactionDetailsView() {
        // Given
        int id = 1;
        String op = "Details";
        Transaction transaction = new Transaction();
        when(transactionService.findById(id)).thenReturn(transaction);

        Model model = mock(Model.class);

        // When
        String result = transactionController.editTransaction(id, op, model);

        // Then
        assertEquals("/accounts/transactionDetails", result);
    }

    @Test
    @DisplayName("save should save the transaction and redirect to transactions view")
    void save_ShouldSaveTransactionAndRedirectToTransactionsView() {
        // Given
        Transaction transaction = new Transaction();

        // When
        String result = transactionController.save(transaction);

        // Then
        assertEquals("redirect:/accounts/transactions", result);
    }

    @Test
    @DisplayName("delete should delete the transaction and redirect to transactions view")
    void delete_ShouldDeleteTransactionAndRedirectToTransactionsView() {
        // Given
        int id = 1;

        // When
        String result = transactionController.delete(id);

        // Then
        assertEquals("redirect:/accounts/transactions", result);
    }
}
