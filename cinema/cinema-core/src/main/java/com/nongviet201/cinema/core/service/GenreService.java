package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.movie.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAllGenres();

    Genre getGenreById(Integer id);

    Genre getGenreByName(String name);

    void createGenre(String name);

    void deleteById(int id);

    void save(Genre genre);
}
