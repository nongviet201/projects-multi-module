package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.model.entity.cinema.Auditorium;
import com.nongviet201.cinema.core.model.entity.cinema.Cinema;
import com.nongviet201.cinema.core.model.entity.cinema.Showtime;
import com.nongviet201.cinema.core.repository.AuditoriumRepository;
import com.nongviet201.cinema.core.repository.CinemaRepository;
import com.nongviet201.cinema.core.repository.ShowtimeRepository;
import com.nongviet201.cinema.core.service.ShowtimeService;
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
        List<Integer> cinemaIds =
            cinemaRepository.findAllByCity_Id(cityId)
            .stream()
            .map(Cinema::getId)
            .collect(Collectors.toList());
        List<Integer> auditoriumIds =
            auditoriumRepository.findAllByCinema_IdIn(cinemaIds)
            .stream()
            .map(Auditorium::getId)
            .collect(Collectors.toList());

        List<Showtime> showtimes =
            showtimeRepository.findAllByMovie_IdAndAuditorium_IdIn(movieId, auditoriumIds);

        return showtimes;
    }

    @Override
    public List<Showtime> getShowtimeByMovieIdAndAuditoriumId(Integer movieId, Integer auditoriumId) {
        return showtimeRepository.findAllByMovie_IdAndAuditorium_IdOrderByStartTimeDesc(movieId, auditoriumId);
    }

    @Override
    public Showtime getShowtimeById(Integer id) {
        return showtimeRepository.findById(id).orElse(null);
    }
}
