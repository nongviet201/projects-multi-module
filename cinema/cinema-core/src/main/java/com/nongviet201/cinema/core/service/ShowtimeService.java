
package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.cinema.Showtime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ShowtimeService {
    List<Showtime> getShowtimeByMovieIdAndAuditoriumId(Integer movieId, Integer auditoriumId);

    Showtime getShowtimeById(Integer id);

    List<Showtime> getShowtimeByMovieIdAndCityId(int movieId, int cityId);

    List<Showtime> getAllShowtimesOnTheSameDayById(Integer showtimeId);

    List<Showtime> getAllShowtimesByMovieIdAnDate(int movieId, LocalDate date);

    List<Showtime> getAllShowtimeByMovieId(int movieId);

    List<Showtime> getDataFiller(LocalDate formDate, LocalDate toDate, Integer cinemaId, Integer movieId);

    boolean checkDataCreate(Integer auditoriumId, Integer movieScheduleId, LocalDate localDate, LocalTime startTime, LocalTime endTime);

    void save(Showtime build);

    void deleteById(Integer id);
}
