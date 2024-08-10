package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.model.entity.cinema.Cinema;
import com.nongviet201.cinema.core.model.enums.*;

public interface BaseTicketPriceService {
    long getPrice(
        SeatType seatType,
        GraphicsType graphicsType,
        ScreeningTimeType screeningTimeType,
        DayType dayType,
        AuditoriumType auditoriumType,
        Cinema cinema
    );
}
