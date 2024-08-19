package com.nongviet201.cinema.core.schedule;

import com.nongviet201.cinema.core.entity.bill.Reservation;
import com.nongviet201.cinema.core.entity.user.TokenConfirm;
import com.nongviet201.cinema.core.model.enums.showtime.ReservationType;
import com.nongviet201.cinema.core.repository.ReservationRepository;
import com.nongviet201.cinema.core.repository.TokenConfirmRepository;
import com.nongviet201.cinema.core.response.ReservationResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;

@Component
@RequiredArgsConstructor
@Lazy
public class ReservationScheduleTask {
    private final ReservationRepository reservationRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final TokenConfirmRepository tokenConfirmRepository;

    @Scheduled(fixedRate = 60000) // 60000 ms = 1 phút
    @Transactional
    public void releaseExpiredReservations() {
        List<Reservation> expiredReservations = reservationRepository
            .findByStatusAndStartOrderTimeBefore(ReservationType.PENDING, now().minusMinutes(10));

        System.out.println("Expired reservations: " + expiredReservations.size() + " " + expiredReservations);

        releaseReservations(expiredReservations);
    }

    @Transactional
    public void releaseExpiredUserDisconnectBookingWS(
        Integer userId
    ) {
        LocalDateTime now = now();

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

//    @Scheduled(fixedRate = 60000) // 60000 ms = 1 phút
//    @Transactional
//    public void tokenExpiredReservations() {
//        List<TokenConfirm> expiredToken =
//            tokenConfirmRepository.findByConfirmedAt(null);
//
//        System.out.println("Expired reservations: " + expiredToken.size() + " " + expiredToken);
//
//        expiredToken.forEach(e ->{
//                if(e.getExpiresAt().isBefore(now())) {
//                    tokenConfirmRepository.delete(e);
//                }
//            });
//    }
}
