package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.cinema.Auditorium;

import java.util.List;

public interface AuditoriumService {
    List<Auditorium> getAllAuditoriumByCinemaId(int id);
}
