package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findAllByStatusAndDeletedOrderByRatingDesc(boolean status, boolean deleted);

    Movie findBySlugAndStatusAndDeleted(String slug, boolean status, boolean deleted);

    List<Movie> findAllByDeletedOrderByReleaseDateDesc(boolean deleted);

    Movie findByIdAndStatusAndDeleted(int id, boolean status, boolean deleted);
}
