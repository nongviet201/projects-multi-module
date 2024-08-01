package com.nongviet201.cinema.web.sdk.controller.rest;

import com.nongviet201.cinema.core.model.entity.cinema.Seat;
import com.nongviet201.cinema.core.service.SeatService;
import com.nongviet201.cinema.web.sdk.controller.service.WebSeatControllerService;
import com.nongviet201.cinema.web.sdk.response.WebSeatResponse;
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

    private final WebSeatControllerService seatControllerService;

    @RequestMapping("/get/{auditoriumId}")
    public ResponseEntity<?> getSeat(
        @PathVariable int auditoriumId
    ) {
        List<WebSeatResponse> seats =
            seatControllerService.getAllSeatsByAuditoriumIdOrderBySeatColumnDesc(auditoriumId);
        return ResponseEntity.ok(seats);
    }
}
