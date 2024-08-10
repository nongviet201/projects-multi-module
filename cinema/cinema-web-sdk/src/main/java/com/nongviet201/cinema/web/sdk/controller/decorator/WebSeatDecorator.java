package com.nongviet201.cinema.web.sdk.controller.decorator;

import com.nongviet201.cinema.core.model.entity.bill.BaseTicketPrice;
import com.nongviet201.cinema.core.model.entity.cinema.Seat;
import com.nongviet201.cinema.core.model.entity.cinema.Showtime;
import com.nongviet201.cinema.core.model.enums.DayType;
import com.nongviet201.cinema.core.service.BaseTicketPriceService;
import com.nongviet201.cinema.web.sdk.converter.WebSeatToResponseConverter;
import com.nongviet201.cinema.web.sdk.response.WebSeatResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
@AllArgsConstructor
public class WebSeatDecorator {
    private final WebSeatToResponseConverter converter;
    private final BaseTicketPriceService baseTicketPriceService;

    public final WebSeatResponse converter(
        Seat seat,
        Showtime showtime
    ) {

        DayType dayType;
        if (showtime.getScreeningDate().getDayOfWeek() == DayOfWeek.SATURDAY ||
            showtime.getScreeningDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
            dayType = DayType.WEEKEND;
        } else {
            dayType = DayType.WEEKDAY;
        }

        long price = baseTicketPriceService.getPrice(
            seat.getType(),
            showtime.getGraphicsType(),
            showtime.getScreeningTimeType(),
            dayType,
            showtime.getAuditorium().getAuditoriumType(),
            showtime.getAuditorium().getCinema()
        );

        return converter.convert(
            seat.getId(),
            seat.getSeatRow(),
            seat.getSeatColumn(),
            seat.isStatus(),
            seat.getType().toString(),
            price
        );
    }
}
