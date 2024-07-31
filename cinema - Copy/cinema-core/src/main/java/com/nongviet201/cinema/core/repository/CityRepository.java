package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.model.entity.cinema.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> findById(int id);
}
