package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.exception.BadRequestException;
import com.nongviet201.cinema.core.model.entity.user.User;
import com.nongviet201.cinema.core.model.entity.bill.Reservation;
import com.nongviet201.cinema.core.model.entity.cinema.Seat;
import com.nongviet201.cinema.core.model.entity.cinema.Showtime;
import com.nongviet201.cinema.core.model.enums.ReservationType;
import com.nongviet201.cinema.core.repository.ReservationRepository;
import com.nongviet201.cinema.core.repository.SeatRepository;
import com.nongviet201.cinema.core.repository.ShowtimeRepository;
import com.nongviet201.cinema.core.repository.UserRepository;
import com.nongviet201.cinema.core.service.ReservationService;
import com.nongviet201.cinema.core.service.SeatService;
import com.nongviet201.cinema.core.service.ShowtimeService;
import com.nongviet201.cinema.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final SeatService seatService;
    private final ShowtimeService showtimeService;

    @Override
    public Reservation createReservation(Integer seatId, Integer showtimeId) {
        User user = userService.getCurrentUser();
        Seat seat = seatService.getSeatById(seatId);
        Showtime showtime = showtimeService.getShowtimeById(showtimeId);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setSeat(seat);
        reservation.setShowTime(showtime);
        reservation.setStatus(ReservationType.PENDING);
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setUpdatedAt(LocalDateTime.now());

        reservationRepository.save(reservation);
        return reservation;
    }

    @Override
    public void updateReservation(Integer id) {
        Reservation reservation = getReservationById(id);
        reservation.setStatus(ReservationType.ORDERED);
        reservationRepository.save(reservation);
    }

    @Override
    public Reservation getReservationById(Integer id) {
        return reservationRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("không tìm thấy thông tin đặt chỗ với id: " + id));
    }

    @Override
    public void removeReservation(Integer id) {
        Reservation reservation =
            reservationRepository.findById(id).orElse(null);
        assert reservation != null;
        reservationRepository.delete(reservation);
    }
}
