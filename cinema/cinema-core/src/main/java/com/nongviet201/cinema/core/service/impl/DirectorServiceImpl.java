package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.movie.Director;
import com.nongviet201.cinema.core.repository.DirectorRepository;
import com.nongviet201.cinema.core.service.DirectorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @AllArgsConstructor public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository directorRepository;


    @Override
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }
}
