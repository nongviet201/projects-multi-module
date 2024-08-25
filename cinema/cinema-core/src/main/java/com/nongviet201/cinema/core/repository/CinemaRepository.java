package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.cinema.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
    Cinema findById(int id);

    List<Cinema> findAllByCity_Id(int cityId);

    List<Cinema> findAllByDeletedOrderByCreatedAt(boolean deleted);
}
