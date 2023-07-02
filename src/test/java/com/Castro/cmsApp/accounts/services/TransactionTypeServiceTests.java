package com.Castro.cmsApp.accounts.services;

import com.Castro.cmsApp.accounts.models.TransactionType;
import com.Castro.cmsApp.accounts.repositories.TransactionTypeRepository;
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

public class TransactionTypeServiceTests {

    private TransactionTypeService transactionTypeService;

    @Mock
    private TransactionTypeRepository transactionTypeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionTypeService = new TransactionTypeService(transactionTypeRepository);
    }

    @Test
    @DisplayName("Test findAll() method")
    public void testFindAll() {
        // Given
        List<TransactionType> transactionTypes = new ArrayList<>();
        transactionTypes.add(new TransactionType());
        transactionTypes.add(new TransactionType());

        when(transactionTypeRepository.findAll()).thenReturn(transactionTypes);

        // When
        List<TransactionType> result = transactionTypeService.findAll();

        // Then
        assertEquals(transactionTypes, result);
    }

    @Test
    @DisplayName("Test findById() method")
    public void testFindById() {
        // Given
        TransactionType transactionType = new TransactionType();
        Optional<TransactionType> optionalTransactionType = Optional.of(transactionType);

        when(transactionTypeRepository.findById(1)).thenReturn(optionalTransactionType);

        // When
        Optional<TransactionType> result = transactionTypeService.findById(1);

        // Then
        assertEquals(optionalTransactionType, result);
    }

    @Test
    @DisplayName("Test delete() method")
    public void testDelete() {
        // Given

        // When
        transactionTypeService.delete(1);

        // Then
        verify(transactionTypeRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Test save() method")
    public void testSave() {
        // Given
        TransactionType transactionType = new TransactionType();

        // When
        transactionTypeService.save(transactionType);

        // Then
        verify(transactionTypeRepository, times(1)).save(transactionType);
    }
}
