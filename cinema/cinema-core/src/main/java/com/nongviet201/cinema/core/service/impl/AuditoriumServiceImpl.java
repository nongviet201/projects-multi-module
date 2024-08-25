package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.cinema.Auditorium;
import com.nongviet201.cinema.core.repository.AuditoriumRepository;
import com.nongviet201.cinema.core.service.AuditoriumService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuditoriumServiceImpl implements AuditoriumService {

    final AuditoriumRepository auditRepository;

    @Override
    public List<Auditorium> getAllAuditoriumByCinemaId(int id) {
        return auditRepository.findAllByCinema_Id(id);
    }
}
