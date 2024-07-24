package com.nongviet201.cinema.web.sdk.service.impl;

import com.nongviet201.cinema.core.model.entity.cinema.Auditorium;
import com.nongviet201.cinema.core.model.entity.cinema.Cinema;
import com.nongviet201.cinema.core.model.entity.cinema.Showtime;
import com.nongviet201.cinema.core.repository.AuditoriumRepository;
import com.nongviet201.cinema.core.repository.CinemaRepository;
import com.nongviet201.cinema.core.repository.ShowtimeRepository;
import com.nongviet201.cinema.web.sdk.service.ShowtimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService {
    private final ShowtimeRepository showtimeRepository;
    private final CinemaRepository cinemaRepository;
    private final AuditoriumRepository auditoriumRepository;

    @Override
    public List<Showtime> getShowtimeByMovieIdAndCityId(int movieId, int cityId) {
        List<Cinema> cinemas = cinemaRepository.findAllByCity_Id(cityId);

        List<Integer> cinemaIds = cinemas.stream().map(Cinema::getId).collect(Collectors.toList());
        List<Auditorium> auditoriums = auditoriumRepository.findAllByCinema_IdIn(cinemaIds);

        List<Integer> auditoriumIds = auditoriums.stream().map(Auditorium::getId).collect(Collectors.toList());
        List<Showtime> showtimes = showtimeRepository.findAllByMovie_IdAndAuditorium_IdIn(movieId, auditoriumIds);

        return showtimes;
    }
}
