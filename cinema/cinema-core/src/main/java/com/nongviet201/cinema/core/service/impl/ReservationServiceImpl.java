package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.exception.BadRequestException;
import com.nongviet201.cinema.core.model.entity.bill.Reservation;
import com.nongviet201.cinema.core.model.entity.cinema.Seat;
import com.nongviet201.cinema.core.model.entity.cinema.Showtime;
import com.nongviet201.cinema.core.model.entity.user.User;
import com.nongviet201.cinema.core.model.enums.ReservationType;
import com.nongviet201.cinema.core.repository.ReservationRepository;
import com.nongviet201.cinema.core.request.ReservationRequest;
import com.nongviet201.cinema.core.response.SeatReservationResponse;
import com.nongviet201.cinema.core.service.ReservationService;
import com.nongviet201.cinema.core.service.SeatService;
import com.nongviet201.cinema.core.service.ShowtimeService;
import com.nongviet201.cinema.core.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final SeatService seatService;
    private final ShowtimeService showtimeService;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    @Transactional
    public void createReservation(
        ReservationRequest request
    ) {
        if (isSeatAvailable(request.getSeatId(), request.getShowtimeId())) {
            throw new BadRequestException("Ghế đã đặt hoặc giữ bởi người dùng khác");
        }

        User user = userService.getCurrentUser();
        Seat seat = seatService.getSeatById(request.getSeatId());
        Showtime showtime = showtimeService.getShowtimeById(request.getShowtimeId());

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setSeat(seat);
        reservation.setShowTime(showtime);
        reservation.setStatus(ReservationType.PENDING);
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setUpdatedAt(LocalDateTime.now());

        reservationRepository.save(reservation);

        SeatReservationResponse response = SeatReservationResponse.builder()
            .seatId(reservation.getSeat().getId())
            .showtimeId(reservation.getShowTime().getId())
            .status(reservation.getStatus())
            .build();

        messagingTemplate.convertAndSend("/topic/seatUpdate", response);
    }

    @Override
    public void updateReservation(Integer id) {
        Reservation reservation = getReservationById(id);
        reservation.setStatus(ReservationType.ORDERED);
        reservationRepository.save(reservation);
    }

    @Override
    public Reservation getReservationById(Integer id) {
        return reservationRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("không tìm thấy thông tin đặt chỗ với id: " + id));
    }

    @Override
    public void removeReservation(
        ReservationRequest request
    ) {
        Reservation reservation = reservationRepository.findBySeat_IdAndShowTime_Id(
            request.getSeatId(),
            request.getShowtimeId()
        ).orElseThrow(() -> new BadRequestException("không tìm thấy thông tin đặt chỗ"));

        reservationRepository.delete(reservation);

        SeatReservationResponse response = SeatReservationResponse.builder()
            .seatId(request.getSeatId())
            .showtimeId(request.getShowtimeId())
            .status(null)
            .build();
        messagingTemplate.convertAndSend("/topic/seatUpdate", response);
    }

    @Override
    public boolean isSeatAvailable(Integer seatId, Integer showtimeId) {
        return reservationRepository.existsBySeat_IdAndShowTime_IdAndStatusIn(
            seatId,
            showtimeId,
            List.of(ReservationType.ORDERED, ReservationType.PENDING)
        );
    }
}
