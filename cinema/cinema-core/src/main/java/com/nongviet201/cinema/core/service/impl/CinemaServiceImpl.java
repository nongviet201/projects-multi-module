package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.cinema.Cinema;
import com.nongviet201.cinema.core.repository.CinemaRepository;
import com.nongviet201.cinema.core.service.CinemaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;

    @Override
    public Cinema getCinemaById(Integer id) {
        return cinemaRepository.findById(id)
            .orElseThrow( ()-> new RuntimeException("Không tìm thấy thông tin rạp với id: " + id));
    }

    @Override
    public List<Cinema> getAllCinemaByDeleted(boolean deleted) {
        return cinemaRepository.findAllByDeletedOrderByCreatedAt(deleted);
    }

    @Override
    public void updateDeletedCinema(int id, boolean deleted) {
        Cinema updatedCinema = getCinemaById(id);
        updatedCinema.setDeleted(deleted);
        cinemaRepository.save(updatedCinema);
    }

    @Override
    public void save(Cinema cinema) {
        cinemaRepository.save(cinema);
    }

}
