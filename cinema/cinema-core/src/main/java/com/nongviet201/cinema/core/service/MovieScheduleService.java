package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.movie.Movie;
import com.nongviet201.cinema.core.entity.movie.MovieSchedule;

import java.util.List;

public interface MovieScheduleService {

    void save(MovieSchedule movieSchedule);

    List<MovieSchedule> getAllMovieSchedule();

    MovieSchedule findByMovieId(Integer movieId);

    void deleteById(Integer id);

    MovieSchedule findById(Integer id);
}
