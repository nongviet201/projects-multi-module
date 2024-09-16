package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.movie.MovieSchedule;
import com.nongviet201.cinema.core.repository.MovieScheduleRepository;
import com.nongviet201.cinema.core.service.MovieScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class MovieScheduleServiceImpl implements MovieScheduleService {

    private final MovieScheduleRepository movieScheduleRepository;

    public void save(
        MovieSchedule movieSchedule
    ) {
        movieScheduleRepository.save(movieSchedule);
    }

    @Override
    public List<MovieSchedule> getAllMovieSchedule() {
        return movieScheduleRepository.findAll();
    }

    @Override
    public MovieSchedule findByMovieId(Integer movieId) {
        return movieScheduleRepository.findByMovie_Id(movieId);
    }

    @Override
    public void deleteById(Integer id) {
        movieScheduleRepository.deleteById(id);
    }

    @Override
    public MovieSchedule findById(Integer id) {
        return movieScheduleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin lịch chiếu phim có id: " + id));
    }

    @Override
    public List<MovieSchedule> getMovieScheduleByDate(
        LocalDate formDate,
        LocalDate toDate
    ) {
        return movieScheduleRepository.findByStartAtBetweenOrderByStartAtAsc(
            formDate,
            toDate
        );
    }

    @Override
    public List<MovieSchedule> getMovieScheduleOnDateAndNotExpired(
        LocalDate date
    ) {
        return movieScheduleRepository.findMovieScheduleNotExpired(
            date
        );
    }

}
