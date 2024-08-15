package com.nongviet201.cinema.web.sdk.controller.rest;

import com.nongviet201.cinema.core.request.ReservationRequest;
import com.nongviet201.cinema.web.sdk.controller.service.WebReservationControllerService;
import com.nongviet201.cinema.web.sdk.response.WebReservationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reservations")
public class ReservationsAPI {
    private final WebReservationControllerService webReservationControllerService;

    @GetMapping("/get/{showtimeId}")
    public ResponseEntity<?> getReservations(
            @PathVariable Integer showtimeId
    ) {
        List<WebReservationResponse> reservations =
                webReservationControllerService.getAllReservationsShowtimeId(showtimeId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createReservation(
            @RequestBody ReservationRequest request
    ) {
        webReservationControllerService.createReservation(
                request
        );
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping("/update-status")
    public ResponseEntity<?> updateStatusReservation(
            @RequestBody Integer id
    ) {
        webReservationControllerService.updateReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelReservation(
            @RequestBody ReservationRequest request
    ) {
        webReservationControllerService.cancelReservation(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
