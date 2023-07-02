package com.Castro.cmsApp.accounts.services;

import com.Castro.cmsApp.accounts.models.Transaction;
import com.Castro.cmsApp.accounts.repositories.TransactionRepository;
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

public class TransactionServiceTests {

    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionService = new TransactionService(transactionRepository);
    }

    @Test
    @DisplayName("Test findAll() method")
    public void testFindAll() {
        // Given
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction());
        transactions.add(new Transaction());

        when(transactionRepository.findAll()).thenReturn(transactions);

        // When
        List<Transaction> result = transactionService.findAll();

        // Then
        assertEquals(transactions, result);
    }

    @Test
    @DisplayName("Test findById() method")
    public void testFindById() {
        // Given
        Transaction transaction = new Transaction();
        Optional<Transaction> optionalTransaction = Optional.of(transaction);

        when(transactionRepository.findById(1)).thenReturn(optionalTransaction);

        // When
        Transaction result = transactionService.findById(1);

        // Then
        assertEquals(transaction, result);
    }

    @Test
    @DisplayName("Test delete() method")
    public void testDelete() {
        // Given

        // When
        transactionService.delete(1);

        // Then
        verify(transactionRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Test save() method")
    public void testSave() {
        // Given
        Transaction transaction = new Transaction();

        // When
        transactionService.save(transaction);

        // Then
        verify(transactionRepository, times(1)).save(transaction);
    }
}
