package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.movie.MovieSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieScheduleRepository extends JpaRepository<MovieSchedule, Integer> {

    MovieSchedule findByMovie_Id(Integer movieId);

    List<MovieSchedule> findByStartAtBetweenOrderByStartAtAsc(LocalDate formDate, LocalDate toDate);

    @Query("SELECT ms FROM MovieSchedule ms WHERE ms.endAt >= :date")
    List<MovieSchedule> findMovieScheduleNotExpired(@Param("date") LocalDate date);
}
