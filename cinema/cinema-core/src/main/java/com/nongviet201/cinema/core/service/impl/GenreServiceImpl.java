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

    @Override
    public Genre getGenreById(Integer id) {
        return genreRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy thể loại có id: " + id));
    }

    @Override
    public Genre getGenreByName(String name) {
        return genreRepository.findByName(name);
    }

    @Override
    public void createGenre(String name) {
        if (getGenreByName(name)!= null) {
            throw new RuntimeException("Thể loại đã tồn tại: " + name);
        }

        genreRepository.save(
            Genre.builder()
               .name(name)
               .build()
        );
    }

    @Override
    public void deleteById(int id) {
        genreRepository.deleteById(id);
    }

    @Override
    public void save(Genre genre) {
        genreRepository.save(genre);
    }
}
