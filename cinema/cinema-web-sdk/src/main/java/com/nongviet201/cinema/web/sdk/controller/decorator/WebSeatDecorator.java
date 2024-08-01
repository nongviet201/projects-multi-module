package com.nongviet201.cinema.web.sdk.controller.decorator;

import com.nongviet201.cinema.core.model.entity.cinema.Seat;
import com.nongviet201.cinema.web.sdk.converter.WebSeatToResponseConverter;
import com.nongviet201.cinema.web.sdk.response.WebSeatResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WebSeatDecorator {
    private final WebSeatToResponseConverter converter;

    public final WebSeatResponse converter(
        Seat seat
    ) {
        return converter.convert(
          seat.getId(),
          seat.getSeatRow(),
          seat.getSeatColumn(),
          seat.isStatus(),
          seat.getType().getName(),
          seat.getType().getPrice()
        );
    }
}
