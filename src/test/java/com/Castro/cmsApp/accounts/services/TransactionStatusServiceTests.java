package com.Castro.cmsApp.accounts.services;

import com.Castro.cmsApp.accounts.models.TransactionStatus;
import com.Castro.cmsApp.accounts.repositories.TransactionStatusRepository;
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

public class TransactionStatusServiceTests {

    private TransactionStatusService transactionStatusService;

    @Mock
    private TransactionStatusRepository transactionStatusRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionStatusService = new TransactionStatusService(transactionStatusRepository);
    }

    @Test
    @DisplayName("Test findAll() method")
    public void testFindAll() {
        // Given
        List<TransactionStatus> transactionStatuses = new ArrayList<>();
        transactionStatuses.add(new TransactionStatus());
        transactionStatuses.add(new TransactionStatus());

        when(transactionStatusRepository.findAll()).thenReturn(transactionStatuses);

        // When
        List<TransactionStatus> result = transactionStatusService.findAll();

        // Then
        assertEquals(transactionStatuses, result);
    }

    @Test
    @DisplayName("Test findById() method")
    public void testFindById() {
        // Given
        TransactionStatus transactionStatus = new TransactionStatus();
        Optional<TransactionStatus> optionalTransactionStatus = Optional.of(transactionStatus);

        when(transactionStatusRepository.findById(1)).thenReturn(optionalTransactionStatus);

        // When
        Optional<TransactionStatus> result = transactionStatusService.findById(1);

        // Then
        assertEquals(optionalTransactionStatus, result);
    }

    @Test
    @DisplayName("Test delete() method")
    public void testDelete() {
        // Given

        // When
        transactionStatusService.delete(1);

        // Then
        verify(transactionStatusRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Test save() method")
    public void testSave() {
        // Given
        TransactionStatus transactionStatus = new TransactionStatus();

        // When
        transactionStatusService.save(transactionStatus);

        // Then
        verify(transactionStatusRepository, times(1)).save(transactionStatus);
    }
}
