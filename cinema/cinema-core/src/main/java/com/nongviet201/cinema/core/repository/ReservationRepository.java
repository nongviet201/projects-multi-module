package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.bill.Reservation;
import com.nongviet201.cinema.core.model.enums.ReservationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    boolean existsBySeatIdAndShowtimeIdAndStatusIn(Integer seatId, Integer showTimeId, List<ReservationType> status);
    boolean existsByUserIdAndShowtimeIdAndSeatIdAndStatus(int userId, int showtimeId, int seatId, ReservationType status);

    Optional<Reservation> findByUserIdAndShowtimeIdAndSeatId(int userId, int showtimeId, int seatId);

    Optional<Reservation> findBySeat_IdAndShowtime_Id(Integer seatId, Integer showtimeId);

    List<Reservation> findAllByShowtime_Id(Integer showtimeId);

    List<Reservation> findByUserIdAndStatus(Integer user_id, ReservationType status);

    List<Reservation> findByStatusAndStartOrderTimeBefore(ReservationType reservationType, LocalDateTime localDateTime);
}
