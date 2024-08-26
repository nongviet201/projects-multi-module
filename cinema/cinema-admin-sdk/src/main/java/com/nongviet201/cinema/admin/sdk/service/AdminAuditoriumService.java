package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.admin.sdk.request.UpsertAuditoriumRequest;
import com.nongviet201.cinema.core.entity.cinema.Auditorium;
import com.nongviet201.cinema.core.model.enums.cinema.AuditoriumType;
import com.nongviet201.cinema.core.service.AuditoriumService;
import com.nongviet201.cinema.core.service.SeatService;
import com.nongviet201.cinema.core.utils.EnumService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AdminAuditoriumService {

    private final EnumService enumService;
    private final AuditoriumService auditoriumService;
    private final AdminCinemaService adminCinemaService;
    private final AdminSeatService adminSeatService;

    public List<Auditorium> getAllDeletedAuditorium() {
        return auditoriumService.getAllAuditoriumByDeleted(true);
    }

    public List<Auditorium> getAllAuditoriumByCinemaId(
        int cinemaId
    ) {
        return auditoriumService.getAllAuditoriumByCinemaId(
            cinemaId
        );
    }


    public void updateAud(
        int id,
        UpsertAuditoriumRequest.AudUpdate request
    ) {
        Auditorium auditorium = auditoriumService.getAuditoriumById(id);

        auditorium.setName(request.getName());
        auditorium.setAuditoriumType(
            enumService.getEnumValueByName(AuditoriumType::valueOf, request.getType(), "AuditoriumType")
        );
        auditorium.setEnabled(request.isEnabled());
        auditoriumService.save(auditorium);
    }

    public AuditoriumType[] getAllAuditoriumTypes() {
        return AuditoriumType.values();
    }

    public void updateDeletedAud(
        int id,
        boolean deleted
    ) {
        Auditorium aud = auditoriumService.getAuditoriumById(id);
        aud.setDeleted(deleted);
        auditoriumService.save(aud);
    }

    public void createAud(
        UpsertAuditoriumRequest.AudCreate request
    ) {
        Auditorium auditorium = Auditorium.builder()
            .name(request.getName())
            .auditoriumType(enumService.getEnumValueByName(AuditoriumType::valueOf, request.getType(), "AuditoriumType"))
            .enabled(request.isEnabled())
            .deleted(false)
            .totalRowChair(request.getTotalRowChair())
            .totalColumnChair(request.getTotalColumnChair())
            .cinema(adminCinemaService.getCinemaById(request.getCinemaId()))
            .build();
        auditoriumService.save(auditorium);
        adminSeatService.seatCreate(
            auditorium
        );
    }

}
