package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.movie.Genre;
import com.nongviet201.cinema.core.repository.GenreRepository;
import com.nongviet201.cinema.core.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}
