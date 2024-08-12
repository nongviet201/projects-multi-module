package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.bill.Reservation;
import com.nongviet201.cinema.core.request.ReservationRequest;

import java.util.List;

public interface ReservationService {
    void createReservation(ReservationRequest request);
    void updateReservation(Integer id);
    Reservation getReservationById(Integer id);
    Reservation getReservationByUserIdAndShowtimeIdAndSeatId(Integer userId, Integer showtimeId, Integer seatId);
    void removeReservation(ReservationRequest request);
    boolean isSeatAvailable(Integer seatId, Integer showtimeId);
    boolean checkPendingReservation(Integer userId, Integer showtimeId, Integer seatId);

    List<Reservation> getAllReservationByShowtimeId(
            int showtimeId
    );


}
