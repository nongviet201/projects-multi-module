package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.model.entity.cinema.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findAllByAuditoriumIdOrderBySeatColumnDesc(int auditoriumId);
}
