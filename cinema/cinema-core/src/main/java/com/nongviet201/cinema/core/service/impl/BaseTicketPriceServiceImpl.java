package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.bill.BaseTicketPrice;
import com.nongviet201.cinema.core.entity.cinema.Cinema;
import com.nongviet201.cinema.core.model.enums.cinema.AuditoriumType;
import com.nongviet201.cinema.core.model.enums.cinema.SeatType;
import com.nongviet201.cinema.core.model.enums.movie.GraphicsType;
import com.nongviet201.cinema.core.model.enums.showtime.DayType;
import com.nongviet201.cinema.core.model.enums.showtime.ScreeningTimeType;
import com.nongviet201.cinema.core.repository.BaseTicketPriceRepository;
import com.nongviet201.cinema.core.service.BaseTicketPriceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BaseTicketPriceServiceImpl implements BaseTicketPriceService {

    private final BaseTicketPriceRepository baseTicketPriceRepository;

    @Override
    public long getPrice(
        SeatType seatType,
        GraphicsType graphicsType,
        ScreeningTimeType screeningTimeType,
        DayType dayType,
        AuditoriumType auditoriumType,
        Cinema cinema
    ) {
        BaseTicketPrice price = baseTicketPriceRepository.findByCinemaAndAuditoriumTypeAndDayTypeAndScreeningTimeTypeAndGraphicsTypeAndSeatType(
            cinema,
            auditoriumType,
            dayType,
            screeningTimeType,
            graphicsType,
            seatType
        );

        return price.getPrice();
    }
}
