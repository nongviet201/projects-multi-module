package com.nongviet201.cinema.web.sdk.controller.service;

import com.nongviet201.cinema.core.entity.cinema.Seat;
import com.nongviet201.cinema.core.entity.cinema.Showtime;
import com.nongviet201.cinema.core.service.SeatService;
import com.nongviet201.cinema.core.service.ShowtimeService;
import com.nongviet201.cinema.web.sdk.controller.decorator.WebSeatDecorator;
import com.nongviet201.cinema.web.sdk.response.WebSeatResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WebSeatControllerService {

    private final SeatService seatService;
    private final WebSeatDecorator webSeatDecorator;
    private final ShowtimeService showtimeService;

    public List<WebSeatResponse> getSeatsByShowtimeId(int ShowtimeId) {
        Showtime showtime = showtimeService.getShowtimeById(ShowtimeId);
        List<Seat> seats = seatService.getAllSeatsByAuditoriumIdOrderBySeatColumnDesc(showtime.getAuditorium().getId());

        List<WebSeatResponse> webSeats = new ArrayList<>();
        for (Seat seat : seats) {
            webSeats.add(webSeatDecorator.converter(seat, showtime));
        }

        return webSeats;
    }
}
