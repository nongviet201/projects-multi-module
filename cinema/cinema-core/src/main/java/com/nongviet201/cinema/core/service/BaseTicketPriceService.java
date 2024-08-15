package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.cinema.Cinema;
import com.nongviet201.cinema.core.model.enums.cinema.AuditoriumType;
import com.nongviet201.cinema.core.model.enums.cinema.SeatType;
import com.nongviet201.cinema.core.model.enums.movie.GraphicsType;
import com.nongviet201.cinema.core.model.enums.showtime.DayType;
import com.nongviet201.cinema.core.model.enums.showtime.ScreeningTimeType;

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
