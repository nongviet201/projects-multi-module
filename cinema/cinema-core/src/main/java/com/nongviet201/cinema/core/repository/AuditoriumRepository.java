package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.cinema.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditorium, Integer> {

    List<Auditorium> findAllByCinema_IdIn(List<Integer> cinemaIds);

    List<Auditorium> findAllByCinema_IdAndDeleted(Integer cinemaId, boolean deleted);

    List<Auditorium> findAllByDeleted(boolean deleted);
}
