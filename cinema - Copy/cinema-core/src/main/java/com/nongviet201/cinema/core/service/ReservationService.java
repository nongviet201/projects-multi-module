package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.model.entity.bill.Reservation;

public interface ReservationService {
    Reservation createReservation(Integer userId, Integer seatId, Integer showtimeId);
    Reservation updateReservation(Integer id);

    void removeReservation(Integer id);
}
