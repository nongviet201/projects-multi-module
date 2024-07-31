package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.model.entity.cinema.Showtime;

import java.util.List;

public interface ShowtimeService {
    List<Showtime> getShowtimeByMovieIdAndAuditoriumId(Integer movieId, Integer auditoriumId);

    Showtime getShowtimeById(Integer id);

    List<Showtime> getShowtimeByMovieId(int movieId);

    List<Showtime> getAllShowtimesOnTheSameDayById(Integer showtimeId);
}
