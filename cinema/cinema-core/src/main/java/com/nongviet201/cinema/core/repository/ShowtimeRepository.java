package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.model.entity.cinema.Showtime;import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    List<Showtime> findAllByMovie_IdAndAuditorium_IdIn(int movieId, List<Integer> auditoriumIds);

    List<Showtime> findAllByMovie_IdAndAuditorium_IdOrderByStartTimeDesc(Integer movieId, Integer auditoriumId);

    Showtime findById(Integer id);
}
