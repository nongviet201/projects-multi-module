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
import com.nongviet201.cinema.core.service.ReservationService;
import com.nongviet201.cinema.web.sdk.request.ReservationRequest;
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
    private final ReservationService reservationService;

    @RequestMapping("/create")
    public ResponseEntity<?> createReservation(@Valid @RequestBody ReservationRequest request) {
        Reservation reservation = reservationService.createReservation(
            request.getUserId(),
            request.getSeatId(),
            request.getShowtimeId()
        );
        return ResponseEntity.ok(reservation);
    }

    @RequestMapping("/update-status")
    public ResponseEntity<?> updateStatusReservation(@Valid @RequestBody Integer id) {
        Reservation reservation = reservationService.updateReservation(id);
        return ResponseEntity.ok(reservation);
    }

    @RequestMapping("/cancel")
    public ResponseEntity<?> cancelReservation(@Valid @RequestBody Integer id) {
        reservationService.removeReservation(id);
        return ResponseEntity.ok("delete complete");
    }
}
