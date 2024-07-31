package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.model.entity.cinema.Seat;

import java.util.List;

public interface SeatService {
    List<Seat> getAllSeatsByAuditoriumIdOrderBySeatColumnDesc(int auditoriumId);
    
}
