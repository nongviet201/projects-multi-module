package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.movie.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    Actor findByName(String name);
}
