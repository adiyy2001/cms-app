package com.Castro.cmsApp.hr.controllers;

import com.Castro.cmsApp.hr.models.JobTitle;
import com.Castro.cmsApp.hr.services.JobTitleService;
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

@DisplayName("JobTitleController Tests")
class JobTitleControllerTests {

    @Mock
    private JobTitleService jobTitleService;

    @InjectMocks
    private JobTitleController jobTitleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("parameters should return jobTitles view with all job titles")
    void parameters_ShouldReturnJobTitlesViewWithAllJobTitles() {
        // Given
        List<JobTitle> jobTitles = new ArrayList<>();
        jobTitles.add(new JobTitle());
        jobTitles.add(new JobTitle());
        when(jobTitleService.findAll()).thenReturn(jobTitles);

        Model model = mock(Model.class);

        // When
        String result = jobTitleController.parameters(model);

        // Then
        assertEquals("hr/jobTitles", result);
        verify(model, times(1)).addAttribute(eq("jobTitles"), eq(jobTitles));
    }

    @Test
    @DisplayName("getById should return the specified job title")
    void getById_ShouldReturnSpecifiedJobTitle() {
        // Given
        int jobTitleId = 1;
        JobTitle jobTitle = new JobTitle();
        when(jobTitleService.findById(jobTitleId)).thenReturn(Optional.of(jobTitle));

        // When
        JobTitle result = jobTitleController.getById(jobTitleId);

        // Then
        assertEquals(jobTitle, result);
    }

    @Test
    @DisplayName("getById should return null if job title is not found")
    void getById_ShouldReturnNullIfJobTitleNotFound() {
        // Given
        int jobTitleId = 1;
        when(jobTitleService.findById(jobTitleId)).thenReturn(Optional.empty());

        // When
        JobTitle result = jobTitleController.getById(jobTitleId);

        // Then
        assertEquals(null, result);
    }

    @Test
    @DisplayName("save should save the job title and redirect to jobTitles view")
    void save_ShouldSaveJobTitleAndRedirectToJobTitlesView() {
        // Given
        JobTitle jobTitle = new JobTitle();

        // When
        String result = jobTitleController.save(jobTitle);

        // Then
        assertEquals("redirect:/hr/jobTitles", result);
        verify(jobTitleService, times(1)).save(jobTitle);
    }

    @Test
    @DisplayName("delete should delete the specified job title and redirect to jobTitles view")
    void delete_ShouldDeleteSpecifiedJobTitleAndRedirectToJobTitlesView() {
        // Given
        int jobTitleId = 1;

        // When
        String result = jobTitleController.delete(jobTitleId);

        // Then
        assertEquals("redirect:/hr/jobTitles", result);
        verify(jobTitleService, times(1)).delete(jobTitleId);
    }
}
