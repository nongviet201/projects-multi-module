package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.model.entity.bill.Reservation;
import com.nongviet201.cinema.core.model.enums.ReservationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    boolean existsBySeat_IdAndShowTime_IdAndStatusIn(Integer seatId, Integer showTimeId, List<ReservationType> status);

    Optional<Reservation> findBySeat_IdAndShowTime_Id(Integer seatId, Integer showtimeId);
}
