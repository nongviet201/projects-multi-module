package com.nongviet201.cinema.core.service.impl;


import com.nongviet201.cinema.core.model.entity.movie.*;
import com.nongviet201.cinema.core.model.request.UpsertMovieRequest;
import com.nongviet201.cinema.core.repository.*;
import com.nongviet201.cinema.core.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;
    private final DirectorRepository directorRepository;
    private final CountryRepository countryRepository;

    @Override
    public Movie createMovie(UpsertMovieRequest request) {
        Optional<Country> country = countryRepository.findById(request.getCountryId());
        List<Genre> genres = genreRepository.findAllById(request.getGenreIds());
        List<Actor> actors = actorRepository.findAllById(request.getActorIds());
        List<Director> directors = directorRepository.findAllById(request.getDirectorIds());

        Movie movie = Movie.builder()
                           .name(request.getMovieName())
                           .slug(generateSlug(request.getMovieName()))
                           .description(request.getDescription())
                           .releaseDate(request.getReleaseDate())
                           .rating(0)
                           .status(request.isStatus())
                           .trailer(request.getTrailer())
                           .createdAt(LocalDate.now())
                           .updateAt(LocalDate.now())
                           .country(country.get())
                           .genres(genres)
                           .actors(actors)
                           .directors(directors)
                           .build();
        movieRepository.save(movie);

        return movie;
    }

    @Override
    public List<Movie> getAllPublishMoviesOrderByReleaseDate() {
        return movieRepository.findAllByStatusOrderByReleaseDateDesc(true   );
    }

    @Override
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
}
