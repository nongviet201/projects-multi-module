package com.nongviet201.cinema.core.schedule;

import com.nongviet201.cinema.core.entity.bill.Reservation;
import com.nongviet201.cinema.core.model.enums.ReservationType;
import com.nongviet201.cinema.core.repository.ReservationRepository;
import com.nongviet201.cinema.core.response.ReservationResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Lazy
public class ReservationScheduleTask {
    private final ReservationRepository reservationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedRate = 60000) // 60000 ms = 1 phút
    @Transactional
    public void releaseExpiredReservations() {
        LocalDateTime now = LocalDateTime.now();

        List<Reservation> expiredReservations = reservationRepository
            .findByStatusAndStartOrderTimeBefore(ReservationType.PENDING, now.minusMinutes(10));

        System.out.println("Expired reservations: " + expiredReservations.size() + " " + expiredReservations);

        releaseReservations(expiredReservations);
    }

    @Transactional
    public void releaseExpiredUserDisconnectBookingWS(
        Integer userId
    ) {
        LocalDateTime now = LocalDateTime.now();

        List<Reservation> expiredReservations = reservationRepository
            .findByUserIdAndStatus(userId, ReservationType.PENDING);

        System.out.println("Expired reservations by user disconnect: " + expiredReservations.size() + " " + expiredReservations);

        releaseReservations(expiredReservations);
    }

    public void releaseReservations(
        List<Reservation> expiredReservations
    ) {
        for (Reservation reservation : expiredReservations) {
            ReservationResponse response = ReservationResponse.builder()
                .seatId(reservation.getSeat().getId())
                .showtimeId(reservation.getShowtime().getId())
                .status(null)
                .build();
            messagingTemplate.convertAndSend("/topic/seatUpdate", response);

            reservationRepository.delete(reservation);
        }
    }
}
