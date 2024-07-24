package com.nongviet201.cinema.core.service.impl;


import com.nongviet201.cinema.core.model.entity.movie.Actor;
import com.nongviet201.cinema.core.repository.ActorRepository;
import com.nongviet201.cinema.core.service.ActorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;

    @Override
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }
}
