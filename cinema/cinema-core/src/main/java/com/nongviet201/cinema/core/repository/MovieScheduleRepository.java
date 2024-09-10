package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.movie.MovieSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieScheduleRepository extends JpaRepository<MovieSchedule, Integer> {

    MovieSchedule findByMovie_Id(Integer movieId);

}
