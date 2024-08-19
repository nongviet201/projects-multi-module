package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.movie.Actor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ActorService {
    List<Actor> getAllActors();
}
