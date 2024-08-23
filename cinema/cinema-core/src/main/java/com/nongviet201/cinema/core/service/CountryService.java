package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.movie.Country;
import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();

    Country getCountryById(Integer id);

    Country getCountryByName(String name);

    void createCountry(String name);

    void deleteById(int id);

    void save(Country country);
}
