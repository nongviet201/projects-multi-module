package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.model.entity.bill.Reservation;

public interface ReservationService {
    Reservation createReservation(Integer seatId, Integer showtimeId);
    void updateReservation(Integer id);
    Reservation getReservationById(Integer id);
    void removeReservation(Integer id);
}
