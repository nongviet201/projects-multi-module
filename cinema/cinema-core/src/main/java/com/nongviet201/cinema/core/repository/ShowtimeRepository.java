
package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.cinema.Showtime;
import com.nongviet201.cinema.core.model.enums.cinema.AuditoriumType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {
    List<Showtime> findAllByMovie_IdOrderByScreeningDateAsc(Integer movieId);
    List<Showtime> findAllByMovie_IdAndAuditoriumTypeAndScreeningDateOrderByStartTimeAsc(Integer movieId, AuditoriumType auditoriumType, LocalDate screeningDate);
    List<Showtime> findAllByMovie_IdAndAuditorium_IdOrderByStartTimeDesc(Integer movieId, Integer auditoriumId);
    List<Showtime> findAllByMovie_IdAndScreeningDateOrderByScreeningDateAsc(int movieId, LocalDate screeningDate);
}
