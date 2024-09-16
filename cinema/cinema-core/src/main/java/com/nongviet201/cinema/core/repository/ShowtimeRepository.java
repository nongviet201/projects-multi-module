
package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.cinema.Showtime;
import com.nongviet201.cinema.core.model.enums.cinema.AuditoriumType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {
    List<Showtime> findAllByMovieSchedule_IdOrderByScreeningDateAsc(Integer movieId);
    List<Showtime> findAllByMovieSchedule_IdAndAuditoriumTypeAndScreeningDateOrderByStartTimeAsc(Integer movieId, AuditoriumType auditoriumType, LocalDate screeningDate);
    List<Showtime> findAllByMovieSchedule_IdAndAuditorium_IdOrderByStartTimeDesc(Integer movieId, Integer auditoriumId);
    List<Showtime> findAllByMovieSchedule_IdAndScreeningDateOrderByScreeningDateAsc(int movieId, LocalDate screeningDate);

    boolean existsByAuditorium_IdAndMovieSchedule_IdAndScreeningDateAndStartTimeBetween(int auditorium_id, Integer movieSchedule_id, LocalDate screeningDate, LocalTime startTime, LocalTime endTime);

    @Query("SELECT s FROM Showtime s " +
        "WHERE s.screeningDate BETWEEN :formDate AND :toDate " +
        "AND (:cinemaId IS NULL OR s.auditorium.cinema.id = :cinemaId) " +
        "AND (:movieId IS NULL OR s.movieSchedule.movie.id = :movieId) " +
        "ORDER BY s.screeningDate ASC")
    List<Showtime> findAllByScreeningDateBetweenAndAuditorium_Cinema_IdAndMovieSchedule_IdOrderByScreeningDateAscStartTimeAsc(
        @Param("formDate") LocalDate formDate,
        @Param("toDate") LocalDate toDate,
        @Param("cinemaId") Integer cinemaId,
        @Param("movieId") Integer movieId
    );
}
