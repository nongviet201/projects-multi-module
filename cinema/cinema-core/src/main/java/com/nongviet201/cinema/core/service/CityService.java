package com.nongviet201.cinema.core.service;


import com.nongviet201.cinema.core.entity.cinema.City;

import java.util.List;

public interface CityService {
    List<City> getAllCities();

    City getCityById(Integer id);
}
