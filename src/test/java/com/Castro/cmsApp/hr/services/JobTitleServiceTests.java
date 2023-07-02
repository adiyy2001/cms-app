package com.Castro.cmsApp.hr.services;

import com.Castro.cmsApp.hr.models.JobTitle;
import com.Castro.cmsApp.hr.repositories.JobTitleRepository;
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

public class JobTitleServiceTests {

    private JobTitleService jobTitleService;

    @Mock
    private JobTitleRepository jobTitleRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        jobTitleService = new JobTitleService(jobTitleRepository);
    }

    @Test
    @DisplayName("Test findAll() method")
    public void testFindAll() {
        // Given
        List<JobTitle> jobTitles = new ArrayList<>();
        jobTitles.add(new JobTitle());
        jobTitles.add(new JobTitle());

        when(jobTitleRepository.findAll()).thenReturn(jobTitles);

        // When
        List<JobTitle> result = jobTitleService.findAll();

        // Then
        assertEquals(jobTitles, result);
    }

    @Test
    @DisplayName("Test findById() method")
    public void testFindById() {
        // Given
        JobTitle jobTitle = new JobTitle();
        Optional<JobTitle> optionalJobTitle = Optional.of(jobTitle);

        when(jobTitleRepository.findById(1)).thenReturn(optionalJobTitle);

        // When
        Optional<JobTitle> result = jobTitleService.findById(1);

        // Then
        assertEquals(optionalJobTitle, result);
    }

    @Test
    @DisplayName("Test delete() method")
    public void testDelete() {
        // Given

        // When
        jobTitleService.delete(1);

        // Then
        verify(jobTitleRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Test save() method")
    public void testSave() {
        // Given
        JobTitle jobTitle = new JobTitle();

        // When
        jobTitleService.save(jobTitle);

        // Then
        verify(jobTitleRepository, times(1)).save(jobTitle);
    }
}
