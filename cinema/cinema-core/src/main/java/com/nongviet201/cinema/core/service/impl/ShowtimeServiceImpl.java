
package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.exception.BadRequestException;
import com.nongviet201.cinema.core.model.entity.cinema.Showtime;
import com.nongviet201.cinema.core.repository.AuditoriumRepository;
import com.nongviet201.cinema.core.repository.CinemaRepository;
import com.nongviet201.cinema.core.repository.ShowtimeRepository;
import com.nongviet201.cinema.core.service.ShowtimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService {
    private final ShowtimeRepository showtimeRepository;

    @Override
    public List<Showtime> getShowtimeByMovieIdAndCityId(int movieId, int cityId) {
        return showtimeRepository.findAllByMovie_IdOrderByScreeningDateAsc(movieId).stream()
            .filter(showtime -> showtime.getAuditorium().getCinema().getCity().getId() == cityId)
            .collect(Collectors.toList());
    }

    @Override
    public List<Showtime> getAllShowtimesOnTheSameDayById(Integer showtimeId) {
        Showtime showtime = getShowtimeById(showtimeId);

        List<Showtime> showTimes = showtimeRepository.findAllByMovie_IdAndAuditoriumTypeAndScreeningDateOrderByStartTimeAsc(
                showtime.getMovie().getId(),
                showtime.getAuditoriumType(),
                showtime.getScreeningDate()
        );

        return showTimes.stream()
                .filter(e -> e.getAuditorium().getCinema().equals(showtime.getAuditorium().getCinema()) && e.getAuditorium().getAuditoriumType() == showtime.getAuditorium().getAuditoriumType())
                .collect(Collectors.toList());
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
        return showtimeRepository.findById(id).orElseThrow(
                () ->  new BadRequestException("không tìm thấy suất chiếu có id: " + id)
        );
    }
}
