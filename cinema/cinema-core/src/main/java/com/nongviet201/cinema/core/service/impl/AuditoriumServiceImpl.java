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
    public Auditorium getAuditoriumById(int id) {
        return auditRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng chiếu có id: " + id))  ;
    }

    @Override
    public List<Auditorium> getAllAuditoriumByCinemaId(int id) {
        return auditRepository.findAllByCinema_IdAndDeleted(id, false);
    }

    @Override
    public List<Auditorium> getAllAuditoriumByDeleted(boolean deleted) {
        return auditRepository.findAllByDeleted(deleted);
    }

    @Override
    public void save(Auditorium auditorium) {
        auditRepository.save(auditorium);
    }

    @Override
    public void delete(Auditorium auditorium) {
        auditRepository.delete(auditorium);
    }
}
