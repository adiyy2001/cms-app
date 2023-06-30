package com.Castro.cmsApp.parameters.restapis;

import com.Castro.cmsApp.parameters.models.Country;
import com.Castro.cmsApp.parameters.services.CountryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CountryRestControllerTests {

    @Test
    @DisplayName("Test getAll() method")
    public void testGetAll() {
        // Given
        List<Country> countries = new ArrayList<Country>();
        countries.add(new Country(1, "US", "Washington D.C.", "United States", "American", "North America", null));
        countries.add(new Country(2, "GB", "London", "United Kingdom", "British", "Europe", null));

        CountryService countryService = Mockito.mock(CountryService.class);
        CountryRestController countryRestController = new CountryRestController(countryService);

        when(countryService.findAll()).thenReturn(countries);

        // When
        List<Country> result = countryRestController.getAll(Mockito.mock(Model.class));

        // Then
        assertEquals(countries, result);
    }
}
