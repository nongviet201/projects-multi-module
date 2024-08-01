package com.nongviet201.cinema.web.sdk.controller.service;

import com.nongviet201.cinema.core.model.entity.cinema.Seat;
import com.nongviet201.cinema.core.service.SeatService;
import com.nongviet201.cinema.web.sdk.controller.decorator.WebSeatDecorator;
import com.nongviet201.cinema.web.sdk.response.WebSeatResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WebSeatControllerService {

    private final SeatService seatService;
    private final WebSeatDecorator webSeatDecorator;

    public List<WebSeatResponse> getAllSeatsByAuditoriumIdOrderBySeatColumnDesc(int auditoriumId) {
        List<Seat> seats = seatService.getAllSeatsByAuditoriumIdOrderBySeatColumnDesc(auditoriumId);
        return seats.stream()
            .map(webSeatDecorator::converter)
            .collect(Collectors.toList());
    }
}
