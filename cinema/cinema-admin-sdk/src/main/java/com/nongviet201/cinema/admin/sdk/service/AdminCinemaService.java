package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.admin.sdk.converter.AdminCinemaRevenueToResponseConverter;
import com.nongviet201.cinema.admin.sdk.request.UpsertAuditoriumRequest;
import com.nongviet201.cinema.admin.sdk.request.UpsertCinemaRequest;
import com.nongviet201.cinema.admin.sdk.response.AdminCinemaRevenueResponse;
import com.nongviet201.cinema.core.entity.bill.Translation;
import com.nongviet201.cinema.core.entity.cinema.Auditorium;
import com.nongviet201.cinema.core.entity.cinema.Cinema;
import com.nongviet201.cinema.core.entity.cinema.City;
import com.nongviet201.cinema.core.model.enums.cinema.AuditoriumType;
import com.nongviet201.cinema.core.service.AuditoriumService;
import com.nongviet201.cinema.core.service.CinemaService;
import com.nongviet201.cinema.core.service.CityService;
import com.nongviet201.cinema.core.service.TranslationService;
import com.nongviet201.cinema.core.utils.EnumService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;


@Service
@AllArgsConstructor
public class AdminCinemaService {

    private final CityService cityService;
    private final CinemaService cinemaService;
    private final TranslationService translationService;
    private final AdminCinemaRevenueToResponseConverter cinemaRevenueToResponseConverter;
    private final EnumService enumService;

    public List<Cinema> getAllCinema() {
        return cinemaService.getAllCinemaByDeleted(false);
    }

    public Cinema getCinemaById(
        int id
    ) {
        return cinemaService.getCinemaById(id);
    }

    public List<Cinema> getAllDeletedCinema() {
        return cinemaService.getAllCinemaByDeleted(true);
    }

    public void updateDeletedCinema(
        int id,
        boolean deleted
    ) {
        cinemaService.updateDeletedCinema(id, deleted);
    };

    public List<AdminCinemaRevenueResponse> getAllCinemaRevenueResponse(
        String time
    ) {
        List<AdminCinemaRevenueResponse> revenueList = new ArrayList<>();
        List<Cinema> cinemaList = getAllCinema();

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

    private List<AdminCinemaRevenueResponse.Manager> getManagersForCinema(
        Cinema cinema
    ) {
        List<AdminCinemaRevenueResponse.Manager> managers = new ArrayList<>();
        cinema.getManager().forEach(e -> {
            managers.add(AdminCinemaRevenueResponse.Manager.builder().name(e.getFullName()).userId(e.getId()).build());
        });
        return managers;
    }

    public void updateCinema(
        int id,
        UpsertCinemaRequest request
    ) {
        Cinema cinema = cinemaService.getCinemaById(id);

        cinema.setName(request.getName());
        cinema.setAddress(request.getAddress());
        cinema.setEnabled(request.isEnabled());
        cinema.setCity(cityService.getCityById(request.getCity()));
        cinema.setLat(request.getLat());
        cinema.setLng(request.getLng());
        cinema.setUpdatedAt(now());
        cinemaService.save(cinema);
    }

    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

}
