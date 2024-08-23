package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.movie.Country;
import com.nongviet201.cinema.core.repository.CountryRepository;
import com.nongviet201.cinema.core.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountryById(Integer id) {
        return countryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy quốc gia có id là: " + id));
    }

    @Override
    public Country getCountryByName(String name) {
        return countryRepository.findByName(name);
    }

    @Override
    public void createCountry(String name) {
        if (getCountryByName(name) != null) {
            throw new RuntimeException("Quốc gia có tên " + name + " đã tồn tại");
        }

        countryRepository.save(
            Country.builder()
                .name(name)
                .build()
        );
    }

    @Override
    public void deleteById(int id) {
        countryRepository.deleteById(id);
    }

    @Override
    public void save(Country country) {
        countryRepository.save(country);
    }
}
