package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.model.entity.cinema.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditorium, Integer> {
    Auditorium findById(int id);

    List<Auditorium> findAllByCinema_IdIn(List<Integer> cinemaIds);
}
