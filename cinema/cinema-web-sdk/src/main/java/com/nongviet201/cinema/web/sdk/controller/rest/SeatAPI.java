package com.nongviet201.cinema.web.sdk.controller.rest;

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
public class SeatAPI {

    private final WebSeatControllerService seatControllerService;

    @RequestMapping("/get/{showtimeId}")
    public ResponseEntity<?> getSeat(
        @PathVariable int showtimeId
    ) {
        List<WebSeatResponse> seats =
            seatControllerService.getSeatsByShowtimeId(showtimeId);
        return ResponseEntity.ok(seats);
    }
}
