package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.movie.Director;
import java.util.List;

public interface DirectorService {
    List<Director> getAllDirectors();

    Director getDirectorById(Integer id);

    Director getDirectorByName(String name);

    void createDirector(String name);

    void deleteById(int id);

    void save(Director director);
}
