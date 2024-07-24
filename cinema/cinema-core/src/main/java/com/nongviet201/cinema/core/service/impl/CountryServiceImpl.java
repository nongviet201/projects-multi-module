package com.nongviet201.cinema.core.service.impl;


import com.nongviet201.cinema.core.model.entity.movie.Country;
import com.nongviet201.cinema.core.repository.CountryRepository;
import com.nongviet201.cinema.core.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @AllArgsConstructor public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
