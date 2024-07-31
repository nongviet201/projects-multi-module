package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.model.entity.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findAllByStatusOrderByReleaseDateDesc(boolean status);

    Movie findBySlugAndStatus(String slug, boolean status);

    List<Movie> findAllByOrderByReleaseDateDesc();
}
