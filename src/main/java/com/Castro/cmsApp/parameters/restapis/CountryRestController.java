package com.Castro.cmsApp.parameters.restapis;

import com.Castro.cmsApp.parameters.models.Country;
import com.Castro.cmsApp.parameters.services.CountryService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryRestController {

    @Autowired
    private final CountryService countryService;

    @CrossOrigin
    @GetMapping("/api/parameters/countries")
    public List<Country> getAll(Model model) {
        List<Country> countries = countryService.findAll();
        return countries;
    }
}
