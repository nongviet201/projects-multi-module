package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.model.entity.movie.Movie;
import com.nongviet201.cinema.core.repository.MovieRepository;
import com.nongviet201.cinema.core.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public List<Movie> getAllPublishMoviesOrderByReleaseDate() {
        return movieRepository.findAllByStatusOrderByReleaseDateDesc(true   );
    }

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getPublishMovieBySlug(String slug) {
        return movieRepository.findBySlugAndStatus(slug, true);
    }

    @Override
    public Movie findById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    public String generateSlug(String title) {
        String slug = title.trim().toLowerCase()
                   .replaceAll("\\s+", "-")
                   .replaceAll("[^a-z0-9-]", "");
        return slug;
    }

    @Override
    public List<Movie> getAllMoviesOderByReleaseDate() {
        return movieRepository.findAllByOrderByReleaseDateDesc();
    }

    @Override
    public Movie getMovieById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public Movie getPublishMovieById(int id) {
        return movieRepository.findByIdAndStatus(id, true);
    }
}
