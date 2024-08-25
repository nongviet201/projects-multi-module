package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.cinema.Cinema;

import java.util.List;

public interface CinemaService {
    Cinema getCinemaById(Integer id);

    List<Cinema> getAllCinemaByDeleted(boolean deleted);

    void updateDeletedCinema(int id, boolean deleted);

    void save(Cinema cinema);
}
