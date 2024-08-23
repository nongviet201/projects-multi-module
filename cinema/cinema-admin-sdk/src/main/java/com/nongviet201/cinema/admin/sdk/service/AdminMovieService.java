package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.core.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminMovieService {

    private final MovieService movieService;

    public void deleteMovie(
        int id
    ) {
        movieService.deleteMovieById(id);
    }
}
