package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.admin.sdk.converter.AdminCinemaRevenueToResponseConverter;
import com.nongviet201.cinema.admin.sdk.request.UpsertCinemaRequest;
import com.nongviet201.cinema.admin.sdk.response.AdminCinemaRevenueResponse;
import com.nongviet201.cinema.core.entity.bill.Transaction;
import com.nongviet201.cinema.core.entity.cinema.Cinema;
import com.nongviet201.cinema.core.entity.cinema.City;
import com.nongviet201.cinema.core.service.CinemaService;
import com.nongviet201.cinema.core.service.CityService;
import com.nongviet201.cinema.core.service.TransactionService;
import com.nongviet201.cinema.core.service.UserService;
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
    private final TransactionService transactionService;
    private final AdminCinemaRevenueToResponseConverter cinemaRevenueToResponseConverter;
    private final EnumService enumService;
    private final UserService userService;

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

            // Lấy danh sách Transaction dựa trên thời gian và trạng thái thành công
            List<Transaction> transactionList =
                transactionService.getAllTranslationByCinemaIdAndTimeAndStatusCode(
                    cinema.getId(),
                    time
                );

            // Tính tổng doanh thu và số lượng vé
            long totalRevenue = transactionList.stream().mapToLong(translation -> translation.getBill().getTotalPrice()).sum();
            int totalTickets = transactionList.size();

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

    public void createCinema(
        UpsertCinemaRequest request
    ) {
        cinemaService.save(
            Cinema.builder()
               .name(request.getName())
               .address(request.getAddress())
               .enabled(request.isEnabled())
               .city(cityService.getCityById(request.getCity()))
               .lat(request.getLat())
               .lng(request.getLng())
                .deleted(false)
               .manager(List.of(userService.getUserById(request.getManagerId())))
               .createdAt(now())
               .updatedAt(now())
               .build()
        );
    }
}
