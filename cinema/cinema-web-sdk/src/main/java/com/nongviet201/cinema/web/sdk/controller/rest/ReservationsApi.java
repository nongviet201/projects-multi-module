package com.nongviet201.cinema.web.sdk.controller.rest;

import com.nongviet201.cinema.core.model.entity.bill.Reservation;
import com.nongviet201.cinema.core.request.ReservationRequest;
import com.nongviet201.cinema.core.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reservations")
public class ReservationsApi {
    private final ReservationService reservationService;

    @PostMapping("/create")
    public ResponseEntity<?> createReservation(
        @RequestBody ReservationRequest request
    ) {
        Reservation reservation = reservationService.createReservation(
            request.getSeatId(),
            request.getShowtimeId()
        );
        return new ResponseEntity<>(reservation, HttpStatus.CREATED); // 201
    }

    @RequestMapping("/update-status")
    public ResponseEntity<?> updateStatusReservation(
        @RequestBody Integer id
    ) {
        reservationService.updateReservation(id);
        return ResponseEntity.ok("thành công");
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<?> cancelReservation(
        @PathVariable Integer id
    ) {
        reservationService.removeReservation(id);
        return ResponseEntity.noContent().build();
    }
}
