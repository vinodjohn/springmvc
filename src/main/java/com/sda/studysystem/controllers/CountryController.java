package com.sda.studysystem.controllers;

import com.sda.studysystem.models.Country;
import com.sda.studysystem.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller to handle Country requests
 *
 * @author VinodJohn
 */
@RestController
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("")
    public List<Country> showAllCountries() {
        return countryService.getAllCountries();
    }

    @PostMapping("/add")
    public ResponseEntity<Country> addCountry(Country country) {
        countryService.createCountry(country);
        return new ResponseEntity<>(country, HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable("id") Long countryId, Country country) {
        country.setId(countryId);
        countryService.updateCountry(country);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable("id") Long countryId) {
        countryService.deleteCountryById(countryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/restore/{id}")
    public ResponseEntity<?> restoreCountry(@PathVariable("id") Long countryId) {
        countryService.restoreCountryById(countryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


