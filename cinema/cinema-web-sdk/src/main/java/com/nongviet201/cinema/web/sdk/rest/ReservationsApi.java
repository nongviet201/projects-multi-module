package com.nongviet201.cinema.web.sdk.rest;

import com.nongviet201.cinema.core.model.entity.User;
import com.nongviet201.cinema.core.model.entity.bill.Reservation;
import com.nongviet201.cinema.core.model.entity.cinema.Seat;
import com.nongviet201.cinema.core.model.entity.cinema.Showtime;
import com.nongviet201.cinema.core.model.enums.ReservationType;
import com.nongviet201.cinema.core.repository.ReservationRepository;
import com.nongviet201.cinema.core.repository.SeatRepository;
import com.nongviet201.cinema.core.repository.ShowtimeRepository;
import com.nongviet201.cinema.core.repository.UserRepository;
import com.nongviet201.cinema.web.sdk.Request.ReservationRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reservations")
public class ReservationsApi {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final SeatRepository seatRepository;
    private final ShowtimeRepository showtimeRepository;

    @RequestMapping("/create")
    public ResponseEntity<?> createReservation(@Valid @RequestBody ReservationRequest request) {
        User user = userRepository.findById(request.getUserId()).orElse(null);
        Seat seat = seatRepository.findById(request.getSeatId()).orElse(null);
        Showtime showtime = showtimeRepository.findById(request.getShowtimeId());

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setSeat(seat);
        reservation.setShowTime(showtime);
        reservation.setStatus(ReservationType.PENDING);
        reservation.setCreateAt(LocalDate.now());
        reservation.setUpdateAt(LocalDate.now());

        reservationRepository.save(reservation);
        return ResponseEntity.ok(reservation);
    }

    @RequestMapping("/update-status")
    public ResponseEntity<?> updateStatusReservation(@Valid @RequestBody Integer id) {
        Reservation reservation = reservationRepository.findById(id);
        reservation.setStatus(ReservationType.ORDERED);
        reservationRepository.save(reservation);
        return ResponseEntity.ok(reservation);
    }
}
