package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.model.entity.bill.Reservation;
import com.nongviet201.cinema.core.request.ReservationRequest;

import java.util.List;

public interface ReservationService {
    void createReservation(ReservationRequest request);
    void updateReservation(Integer id);
    Reservation getReservationById(Integer id);
    void removeReservation(ReservationRequest request);
    boolean isSeatAvailable(Integer seatId, Integer showtimeId);

    List<Reservation> getAllReservationByShowtimeId(
            int showtimeId
    );
}
