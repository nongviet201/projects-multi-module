package com.nongviet201.cinema.web.sdk.rest;

import com.nongviet201.cinema.core.model.entity.cinema.Seat;
import com.nongviet201.cinema.core.service.SeatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/seat")
public class SeatApi {
    private final SeatService seatService;

    @RequestMapping("/get/{auditoriumId}")
    public ResponseEntity<?> getSeat(
        @PathVariable int auditoriumId
    ) {
        List<Seat> seats =
            seatService.getAllSeatsByAuditoriumIdOrderBySeatColumnDesc(auditoriumId);
        return ResponseEntity.ok(seats);
    }
}
