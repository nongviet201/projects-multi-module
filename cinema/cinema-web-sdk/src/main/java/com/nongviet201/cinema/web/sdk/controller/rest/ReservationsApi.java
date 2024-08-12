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
        reservationService.createReservation(
            request
        );
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping("/update-status")
    public ResponseEntity<?> updateStatusReservation(
        @RequestBody Integer id
    ) {
        reservationService.updateReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<?> cancelReservation(
        @RequestBody ReservationRequest request
    ) {
        reservationService.removeReservation(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
