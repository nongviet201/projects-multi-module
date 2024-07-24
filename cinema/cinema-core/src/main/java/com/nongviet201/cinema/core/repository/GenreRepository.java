package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.model.entity.movie.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {}
