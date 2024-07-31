package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.model.entity.cinema.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatTypeRepository extends JpaRepository<SeatType, Integer> {
}
