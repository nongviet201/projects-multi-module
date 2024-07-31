package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.model.entity.bill.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findBySeat_IdAndShowTime_Id(Integer seatId, Integer showTimeId);
}
