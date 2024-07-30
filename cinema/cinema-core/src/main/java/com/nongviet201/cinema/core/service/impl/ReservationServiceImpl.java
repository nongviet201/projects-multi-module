package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.model.entity.User;
import com.nongviet201.cinema.core.model.entity.bill.Reservation;
import com.nongviet201.cinema.core.model.entity.cinema.Seat;
import com.nongviet201.cinema.core.model.entity.cinema.Showtime;
import com.nongviet201.cinema.core.model.enums.ReservationType;
import com.nongviet201.cinema.core.repository.ReservationRepository;
import com.nongviet201.cinema.core.repository.SeatRepository;
import com.nongviet201.cinema.core.repository.ShowtimeRepository;
import com.nongviet201.cinema.core.repository.UserRepository;
import com.nongviet201.cinema.core.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final SeatRepository seatRepository;
    private final ShowtimeRepository showtimeRepository;

    @Override
    public Reservation createReservation(Integer userId, Integer seatId, Integer showtimeId) {
        User user = userRepository.findById(userId).orElse(null);
        Seat seat = seatRepository.findById(seatId).orElse(null);
        Showtime showtime = showtimeRepository.findById(showtimeId).orElse(null);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setSeat(seat);
        reservation.setShowTime(showtime);
        reservation.setStatus(ReservationType.PENDING);
        reservation.setCreateAt(LocalDate.now());
        reservation.setUpdateAt(LocalDate.now());

        reservationRepository.save(reservation);
        return reservation;
    }

    @Override
    public Reservation updateReservation(Integer id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        assert reservation != null;
        reservation.setStatus(ReservationType.ORDERED);
        reservationRepository.save(reservation);
        return null;
    }

    @Override
    public void removeReservation(Integer id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        assert reservation != null;
        reservationRepository.delete(reservation);
    }
}
