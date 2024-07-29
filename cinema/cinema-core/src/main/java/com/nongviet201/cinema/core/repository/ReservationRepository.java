package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.model.entity.bill.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation  findById(Integer id);
}
