package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.cinema.Auditorium;

import java.util.List;

public interface AuditoriumService {
    Auditorium getAuditoriumById (int id);
    List<Auditorium> getAllAuditoriumByCinemaId(int id);
    List<Auditorium> getAllAuditoriumByDeleted(boolean deleted);
    void save(Auditorium auditorium);
    void delete(Auditorium auditorium);
}
