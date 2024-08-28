package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.exception.BadRequestException;
import com.nongviet201.cinema.core.entity.cinema.Seat;
import com.nongviet201.cinema.core.repository.SeatRepository;
import com.nongviet201.cinema.core.service.SeatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    @Override
    public List<Seat> getAllSeatsByAuditoriumIdOrderBySeatRowAsc(int auditoriumId) {
        return seatRepository.findAllByAuditoriumIdAndDeletedOrderBySeatRowAscSeatColumnAsc(
            auditoriumId,
            false
        );
    }

    @Override
    public List<Seat> getAllSeatDeletedByAuditoriumIdOrderBySeatRowAsc(int audId) {
        return seatRepository.findAllByAuditoriumIdAndDeletedOrderBySeatRowAscSeatColumnAsc(
            audId,
            true
        );
    }

    @Override
    public Seat getSeatById(int seatId) {
        return seatRepository.findById(seatId)
                .orElseThrow(() -> new BadRequestException("không tìm thấy ghế với id: " + seatId));
    }

    @Override
    public void save(Seat seat) {
        seatRepository.save(seat);
    }

    @Override
    public void delete(int id) {
        seatRepository.deleteById(id);
    }
}
