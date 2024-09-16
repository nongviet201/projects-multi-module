
package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.exception.BadRequestException;
import com.nongviet201.cinema.core.entity.cinema.Showtime;
import com.nongviet201.cinema.core.repository.ShowtimeRepository;
import com.nongviet201.cinema.core.service.ShowtimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService {
    private final ShowtimeRepository showtimeRepository;

    @Override
    public List<Showtime> getShowtimeByMovieIdAndCityId(int movieId, int cityId) {
        return showtimeRepository.findAllByMovieSchedule_IdOrderByScreeningDateAsc(movieId).stream()
            .filter(showtime -> showtime.getAuditorium().getCinema().getCity().getId() == cityId)
            .collect(Collectors.toList());
    }

    @Override
    public List<Showtime> getAllShowtimesOnTheSameDayById(Integer showtimeId) {
        Showtime showtime = getShowtimeById(showtimeId);

        List<Showtime> showTimes = showtimeRepository.findAllByMovieSchedule_IdAndAuditoriumTypeAndScreeningDateOrderByStartTimeAsc(
                showtime.getMovieSchedule().getMovie().getId(),
                showtime.getAuditoriumType(),
                showtime.getScreeningDate()
        );

        return showTimes.stream()
                .filter(e -> e.getAuditorium().getCinema().equals(showtime.getAuditorium().getCinema()) && e.getAuditorium().getAuditoriumType() == showtime.getAuditorium().getAuditoriumType())
                .collect(Collectors.toList());
    }

    @Override
    public List<Showtime> getAllShowtimesByMovieIdAnDate(int movieId, LocalDate date) {
        return showtimeRepository.findAllByMovieSchedule_IdAndScreeningDateOrderByScreeningDateAsc(movieId, date);
    }

    @Override
    public List<Showtime> getAllShowtimeByMovieId(int movieId) {
        return showtimeRepository.findAllByMovieSchedule_IdOrderByScreeningDateAsc(movieId);
    }

    public List<Showtime> getDataFiller(
        LocalDate formDate,
        LocalDate toDate,
        Integer cinemaId,
        Integer movieId
    ) {
        return showtimeRepository.findAllByScreeningDateBetweenAndAuditorium_Cinema_IdAndMovieSchedule_IdOrderByScreeningDateAscStartTimeAsc(
            formDate,
            toDate,
            cinemaId,
            movieId
        );
    }

    @Override
    public boolean checkDataCreate(
        Integer auditoriumId,
        Integer movieScheduleId,
        LocalDate localDate,
        LocalTime startTime,
        LocalTime endTime
    ) {
        return showtimeRepository.existsByAuditorium_IdAndMovieSchedule_IdAndScreeningDateAndStartTimeBetween(
            auditoriumId,
            movieScheduleId,
            localDate,
            startTime,
            endTime
        );
    }

    @Override
    public void save(Showtime build) {
        showtimeRepository.save(build);
    }

    @Override
    public void deleteById(Integer id) {
        showtimeRepository.deleteById(id);
    }

    @Override
    public List<Showtime> getShowtimeByMovieIdAndAuditoriumId(Integer movieId, Integer auditoriumId) {
        return showtimeRepository.findAllByMovieSchedule_IdAndAuditorium_IdOrderByStartTimeDesc(movieId, auditoriumId);
    }

    @Override
    public Showtime getShowtimeById(Integer id) {
        return showtimeRepository.findById(id).orElseThrow(
                () ->  new BadRequestException("không tìm thấy suất chiếu có id: " + id)
        );
    }
}
