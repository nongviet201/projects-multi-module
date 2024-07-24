package com.nongviet201.cinema.admin.sdk.service.impl;

import com.nongviet201.cinema.admin.sdk.service.MovieService;
import com.nongviet201.cinema.core.model.entity.movie.Movie;
import com.nongviet201.cinema.core.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMoviesOderByReleaseDate() {
        return movieRepository.findAllByOrderByReleaseDateDesc();
    }

    @Override
    public Movie getMovieById(int id) {
        return movieRepository.findById(id).orElse(null);
    }
}
