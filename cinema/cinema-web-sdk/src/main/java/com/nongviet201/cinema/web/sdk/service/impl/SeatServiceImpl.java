package com.nongviet201.cinema.web.sdk.service.impl;

import com.nongviet201.cinema.core.model.entity.cinema.Seat;
import com.nongviet201.cinema.core.repository.SeatRepository;
import com.nongviet201.cinema.web.sdk.service.SeatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    @Override
    public List<Seat> getAllSeatsByAuditoriumIdOrderBySeatColumnDesc(int auditoriumId) {
        return seatRepository.findAllByAuditoriumIdOrderBySeatColumnDesc(auditoriumId)  ;
    }
}
