package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.movie.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {

    Director findByName(String name);
}
