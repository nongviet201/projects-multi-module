package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.cinema.City;
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

    @Override
    public City getCityById(Integer id) {
        return cityRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy thành phố với id: " + id))
            ;
    }
}
