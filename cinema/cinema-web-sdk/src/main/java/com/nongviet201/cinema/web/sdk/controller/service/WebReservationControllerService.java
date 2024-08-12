package com.nongviet201.cinema.web.sdk.controller.service;

import com.nongviet201.cinema.core.entity.bill.Reservation;
import com.nongviet201.cinema.core.request.ReservationRequest;
import com.nongviet201.cinema.core.service.ReservationService;
import com.nongviet201.cinema.web.sdk.controller.decorator.WebReservationDecorator;
import com.nongviet201.cinema.web.sdk.response.WebReservationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WebReservationControllerService {

    private final ReservationService reservationService;
    private final WebReservationDecorator reservationDecorator;

    public List<WebReservationResponse> getAllReservationsShowtimeId(
            int id
    ) {
        List<Reservation> reservations = reservationService.getAllReservationByShowtimeId(id);
        return reservations.stream()
                .map(reservationDecorator::decorate)
                .collect(Collectors.toList());
    }

    public void createReservation(
            ReservationRequest request
    ) {
        reservationService.createReservation(request);
    }

    public void updateReservation(
            Integer id
    ) {
        reservationService.updateReservation(id);
    }

    public void cancelReservation(
            ReservationRequest request
    ) {
        reservationService.removeReservation(request);
    }
}
