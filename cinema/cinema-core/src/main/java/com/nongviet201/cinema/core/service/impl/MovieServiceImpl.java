package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.movie.Movie;
import com.nongviet201.cinema.core.repository.MovieRepository;
import com.nongviet201.cinema.core.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public List<Movie> getAllPublishMoviesOrderByRating() {
        return movieRepository.findAllByStatusAndDeletedOrderByRatingDesc(true, false);
    }

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getPublishMovieBySlug(String slug) {
        return movieRepository.findBySlugAndStatusAndDeleted(slug, true, false);
    }

    public String generateSlug(String title) {
        return title.trim().toLowerCase()
                   .replaceAll("\\s+", "-")
                   .replaceAll("[^a-z0-9-]", "");
    }

    @Override
    public List<Movie> getAllMoviesOderByReleaseDate() {
        return movieRepository.findAllByDeletedOrderByReleaseDateDesc(false);
    }

    @Override
    public Movie getMovieById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public Movie getPublishMovieById(int id) {
        return movieRepository.findByIdAndStatusAndDeleted(id, true, false);
    }


    @Override
    public double updateRating(int movieId, int rating) {
        Movie movie = getMovieById(movieId);
        movie.setRatingCount(movie.getRatingCount() + 1);
        movie.setTotalRatings(movie.getTotalRatings() + rating);

        // Tính toán rating
        double newRating = movie.getTotalRatings() / (double) movie.getRatingCount();

        // Làm tròn đến 1 chữ số thập phân
        BigDecimal bd = new BigDecimal(newRating).setScale(1, RoundingMode.HALF_UP);
        movie.setRating(bd.doubleValue());

        movieRepository.save(movie);
        return movie.getRating();
    }

    @Override
    public List<Movie> getAllDeletedMovie() {
        return movieRepository.findAllByDeletedOrderByReleaseDateDesc(true);
    }

}
