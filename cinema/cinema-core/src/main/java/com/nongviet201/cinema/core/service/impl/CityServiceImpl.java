package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.model.entity.cinema.City;
import com.nongviet201.cinema.core.repository.CityRepository;
import com.nongviet201.cinema.core.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
