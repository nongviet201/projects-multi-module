
package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.model.entity.cinema.Showtime;
import com.nongviet201.cinema.core.repository.AuditoriumRepository;
import com.nongviet201.cinema.core.repository.CinemaRepository;
import com.nongviet201.cinema.core.repository.ShowtimeRepository;
import com.nongviet201.cinema.core.service.ShowtimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService {
    private final ShowtimeRepository showtimeRepository;

    @Override
    public List<Showtime> getShowtimeByMovieIdCityId(int movieId, int cityId) {
        return showtimeRepository.findAllByMovie_IdOrderByScreeningDateAsc(movieId).stream()
            .filter(showtime -> showtime.getAuditorium().getCinema().getId() == cityId)
            .collect(Collectors.toList());
    }

    @Override
    public List<Showtime> getAllShowtimesOnTheSameDayById(Integer showtimeId) {
        Showtime showtime = showtimeRepository.findById(showtimeId).orElse(null);
        assert showtime != null;
        return showtimeRepository
            .findAllByMovie_IdAndAuditorium_IdAndScreeningDateOrderByStartTimeAsc(
                showtime.getMovie().getId(),
                showtime.getAuditorium().getId(),
                showtime.getScreeningDate()
            );
    }

    @Override
    public List<Showtime> getAllShowtimesByMovieIdAnDate(int movieId, LocalDate date) {
        return showtimeRepository.findAllByMovie_IdAndScreeningDateOrderByScreeningDateAsc(movieId, date);
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
