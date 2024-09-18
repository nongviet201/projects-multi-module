package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.bill.Bill;
import com.nongviet201.cinema.core.entity.bill.Reservation;
import com.nongviet201.cinema.core.entity.bill.Transaction;
import com.nongviet201.cinema.core.entity.cinema.Showtime;
import com.nongviet201.cinema.core.entity.user.User;
import com.nongviet201.cinema.core.entity.user.UserStatistic;
import com.nongviet201.cinema.core.exception.BadRequestException;
import com.nongviet201.cinema.core.model.enums.bill.BillStatus;
import com.nongviet201.cinema.core.model.enums.bill.PaymentMethod;
import com.nongviet201.cinema.core.repository.BillRepository;
import com.nongviet201.cinema.core.repository.TransactionRepository;
import com.nongviet201.cinema.core.request.BillRequestDTO;
import com.nongviet201.cinema.core.request.ToPaymentRequest;
import com.nongviet201.cinema.core.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.time.LocalDateTime.*;

@Service
@AllArgsConstructor
public class BillServiceImpl implements BillService {
    private final UserService userService;
    private final ShowtimeService showtimeService;
    private final ReservationService reservationService;
    private final BillRepository billRepository;
    private final BillSeatService billSeatService;
    private final BillComboService billComboService;
    private final TransactionRepository transactionRepository;
    private final PaymentService paymentMethodService;
    private final UserStatisticService userStatisticService;

    @Override
    public String createBill(
        BillRequestDTO.PaymentRequest paymentRequest
    ) {
        User user = userService.getCurrentUser();

        UserStatistic userStatistic = userStatisticService.getUserStatisticByUserId(user.getId());

        if (paymentRequest.getPoints() > userStatistic.getPoints()) {
            throw new BadRequestException("User hiện tại không đủ số Points để thực hiện thao tác");
        }

        Showtime showtime = showtimeService.getShowtimeById(
            paymentRequest.getShowtimeId()
        );

        paymentRequest.getSeatRequest().forEach(seatId -> {
            if (!reservationService.checkPendingReservation(
                user.getId(),
                showtime.getId(),
                seatId
            )) {
                throw new BadRequestException("Không tìm thấy thông tin đặt chỗ");
            }
        });

        Bill bill = Bill.builder()
            .user(user)
            .showtime(showtime)
            .discount(paymentRequest.getPoints() * 1000)
            .totalPrice(0)
            .status(BillStatus.PENDING_PAYMENT)
            .createdAt(now())
            .updatedAt(now())
            .cinema(showtime.getAuditorium().getCinema())
            .build();
        billRepository.save(bill);

        long totalPrice = 0;

        for (BillRequestDTO.ComboRequest combo : paymentRequest.getComboRequest()) {
            totalPrice += billComboService.createBillCombo(
                bill.getId(),
                combo.getComboId(),
                combo.getQuantity()
            );
        }

        for (int seatId : paymentRequest.getSeatRequest()) {
            totalPrice += billSeatService.createBillSeat(
                bill.getId(),
                seatId
            );
        }

        Transaction transaction = Transaction.builder()
            .status(false)
            .bill(bill)
            .createdAt(now())
            .updatedAt(now())
            .paymentMethod(PaymentMethod.values()[paymentRequest.getPaymentMethod()-1])
            .cinema(showtime.getAuditorium().getCinema())
            .build();
        transactionRepository.save(transaction);

        totalPrice -= (paymentRequest.getPoints() * 1000);
        bill.setTotalPrice(totalPrice);
        billRepository.save(bill);

        Reservation reservation = reservationService.getReservationByUserIdAndShowtimeIdAndSeatId(
            user.getId(),
            showtime.getId(),
            paymentRequest.getSeatRequest().getFirst()
        );

        Integer timeRemain = reservation.getStartOrderTime().plusMinutes(10).minusMinutes(now().getMinute()).getMinute();

        return paymentMethodService.transitionToPaymentMethod(
            ToPaymentRequest.builder()
                .translationId(transaction.getId())
                .amount(totalPrice)
                .timeRemain(timeRemain)
                .paymentMethod(transaction.getPaymentMethod())
                .build()
        );
    }

    @Override
    public Bill getBillById(Integer id) {
        return billRepository.findById(id).orElseThrow(
            () -> new BadRequestException("Không tìm thấy hóa đơn")
        );
    }

    @Override
    public List<Bill> clientGetBillUserProfile() {
        return billRepository.findByUserIdAndStatusOrderByPaymentAtDesc(
            userService.getCurrentUser().getId(),
            BillStatus.PAID
        );
    }

    @Override
    public Bill clientGetBillDetailById(
        Integer id
    ) {
        Bill bill = getBillById(id);

        if (!Objects.equals(bill.getUser().getId(), userService.getCurrentUser().getId())){
            throw new BadRequestException("Tài khoản yêu cầu không khớp với thông tin tài khoản trên hóa đơn");
        }

        return bill;
    }
}
