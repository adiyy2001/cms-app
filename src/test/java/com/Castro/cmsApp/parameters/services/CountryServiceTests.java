package com.Castro.cmsApp.parameters.services;

import com.Castro.cmsApp.parameters.models.Country;
import com.Castro.cmsApp.parameters.repositories.CountryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
@DisplayName("CountryService Tests")
class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryService countryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findAll should return all countries")
    void findAll_ShouldReturnAllCountries() {
        // Given
        List<Country> countries = new ArrayList<>();
        countries.add(new Country());
        countries.add(new Country());

        when(countryRepository.findAll()).thenReturn(countries);

        // When
        List<Country> result = countryService.findAll();

        // Then
        assertEquals(countries.size(), result.size());
        verify(countryRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findPage should return a page of countries")
    void findPage_ShouldReturnPageOfCountries() {
        // Given
        int pageNumber = 1;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Country> countryPage = mock(Page.class);

        when(countryRepository.findAll(pageable)).thenReturn(countryPage);

        // When
        Page<Country> result = countryService.findPage(pageNumber);

        // Then
        assertEquals(countryPage, result);
        verify(countryRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("save should save the country")
    void save_ShouldSaveCountry() {
        // Given
        Country country = new Country();

        // When
        countryService.save(country);

        // Then
        verify(countryRepository, times(1)).save(country);
    }

    @Test
    @DisplayName("delete should delete the country")
    void delete_ShouldDeleteCountry() {
        // Given
        int id = 1;

        // When
        countryService.delete(id);

        // Then
        verify(countryRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("getById with valid ID should return the corresponding country")
    void getById_WithValidId_ShouldReturnCountry() {
        // Given
        int id = 1;
        Country country = new Country();
        when(countryRepository.findById(id)).thenReturn(Optional.of(country));

        // When
        Country result = countryService.getById(id);

        // Then
        assertEquals(country, result);
        verify(countryRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("getById with invalid ID should return null")
    void getById_WithInvalidId_ShouldReturnNull() {
        // Given
        int id = 1;
        when(countryRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Country result = countryService.getById(id);

        // Then
        assertEquals(null, result);
        verify(countryRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("update should update the country")
    void update_ShouldUpdateCountry() {
        // Given
        Country country = new Country();

        // When
        countryService.update(country);

        // Then
        verify(countryRepository, times(1)).save(country);
    }

    @Test
    @DisplayName("findByKeyword should return countries matching the keyword")
    void findByKeyword_ShouldReturnMatchingCountries() {
        // Given
        String keyword = "example";
        List<Country> countries = new ArrayList<>();
        countries.add(new Country());
        countries.add(new Country());

        when(countryRepository.findByKeyword(keyword)).thenReturn(countries);

        // When
        List<Country> result = countryService.findByKeyword(keyword);

        // Then
        assertEquals(countries.size(), result.size());
        verify(countryRepository, times(1)).findByKeyword(keyword);
    }

    @Test
    @DisplayName("findAllWithSort should return a sorted page of countries")
    void findAllWithSort_ShouldReturnSortedPageOfCountries() {
        // Given
        String field = "name";
        String direction = "ASC";
        int pageNumber = 1;
        int pageSize = 5;
        Sort sort = Sort.by(field).ascending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        Page<Country> countryPage = mock(Page.class);

        when(countryRepository.findAll(pageable)).thenReturn(countryPage);

        // When
        Page<Country> result = countryService.findAllWithSort(field, direction, pageNumber);

        // Then
        assertEquals(countryPage, result);
        verify(countryRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("findAllWithSort should return a sorted page of countries in descending order")
    void findAllWithSort_ShouldReturnSortedPageOfCountriesDescending() {
        // Given
        String field = "name";
        String direction = "DESC";
        int pageNumber = 1;
        int pageSize = 5;
        Sort sort = Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        Page<Country> countryPage = mock(Page.class);

        when(countryRepository.findAll(pageable)).thenReturn(countryPage);

        // When
        Page<Country> result = countryService.findAllWithSort(field, direction, pageNumber);

        // Then
        assertEquals(countryPage, result);
        verify(countryRepository, times(1)).findAll(pageable);
    }

}
