package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.admin.sdk.converter.AdminCinemaRevenueToResponseConverter;
import com.nongviet201.cinema.admin.sdk.response.AdminCinemaRevenueResponse;
import com.nongviet201.cinema.core.entity.bill.Translation;
import com.nongviet201.cinema.core.entity.cinema.Cinema;
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
    private final AdminCinemaRevenueToResponseConverter cinemaRevenueToResponseConverter;

    public List<AdminCinemaRevenueResponse> getAllCinemaRevenueResponse(
        String time
    ) {
        List<AdminCinemaRevenueResponse> revenueList = new ArrayList<>();
        List<Cinema> cinemaList = cinemaService.getAllCinemas();

        for (Cinema cinema : cinemaList) {
            List<AdminCinemaRevenueResponse.Manager> managers = getManagersForCinema(cinema);

            // Lấy danh sách Translation dựa trên thời gian và trạng thái thành công
            List<Translation> translationList =
                translationService.getAllTranslationByCinemaIdAndTimeAndStatusCode(
                    cinema.getId(),
                    time
                );

            // Tính tổng doanh thu và số lượng vé
            long totalRevenue = translationList.stream().mapToLong(translation -> translation.getBill().getTotalPrice()).sum();
            int totalTickets = translationList.size();

            revenueList.add(
                cinemaRevenueToResponseConverter.convert(
                    cinema.getId(),
                    cinema.getName(),
                    totalTickets,
                    totalRevenue,
                    managers
                )
            );
        }

        return revenueList;
    }

    private List<AdminCinemaRevenueResponse.Manager> getManagersForCinema(Cinema cinema) {
        List<AdminCinemaRevenueResponse.Manager> managers = new ArrayList<>();
        cinema.getManager().forEach(e -> {
            managers.add(AdminCinemaRevenueResponse.Manager.builder().name(e.getFullName()).userId(e.getId()).build());
        });
        return managers;
    }

}
