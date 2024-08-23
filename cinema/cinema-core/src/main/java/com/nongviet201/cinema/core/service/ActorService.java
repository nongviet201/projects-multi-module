package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.movie.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> getAllActors();

    Actor getActorById(Integer id);

    Actor getActorByName(String name);

    void createActor(String name);

    void deleteById(int id);

    void save(Actor actor);
}
