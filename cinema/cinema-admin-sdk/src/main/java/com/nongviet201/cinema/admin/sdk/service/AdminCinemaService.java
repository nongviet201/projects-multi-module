package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.admin.sdk.response.AdminCinemaRevenueResponse;
import com.nongviet201.cinema.core.entity.bill.Translation;
import com.nongviet201.cinema.core.entity.cinema.Cinema;
import com.nongviet201.cinema.core.model.enums.bill.BillStatus;
import com.nongviet201.cinema.core.service.CinemaService;
import com.nongviet201.cinema.core.service.TranslationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminCinemaService {

    private final CinemaService cinemaService;
    private final TranslationService translationService;

    public List<AdminCinemaRevenueResponse> getAllCinemaRevenueResponse(
        String time
    ) {
        List<AdminCinemaRevenueResponse> revenueList = new ArrayList<>();

        List<Cinema> cinemaList = cinemaService.getAllCinemas();

        cinemaList.forEach(cinema -> {
            List<AdminCinemaRevenueResponse.Manager> managers = new ArrayList<>();
            managers.add(
                AdminCinemaRevenueResponse.Manager.builder()
                    .name("Nông Việt")
                    .userId(2)
                    .build()
            );

       /*     cinema.getManager().forEach(e -> {
                managers.add(
                    AdminCinemaRevenueResponse.Manager.builder()
                        .name(e.getFullName())
                        .userId(e.getId())
                        .build()
                );
            });*/

            List<Translation> translationList =
                translationService.getAllTranslationByCinemaIdAndTimeAndBillStatus(
                    cinema.getId(),
                    time
                );

            revenueList.add(
                AdminCinemaRevenueResponse.builder()
                    .name(cinema.getName())
                    .cinemaId(cinema.getId())
                    .totalTickets(translationList.size())
                    .totalRevenue(translationList.stream()
                        .mapToLong(e -> e.getBill().getTotalPrice())
                        .sum()
                    )
                    .kpiPercent(65)
                    .totalKpi(100000000L)
                    .managers(managers)
                    .build()
            );
        });
        return revenueList;
    }
}
