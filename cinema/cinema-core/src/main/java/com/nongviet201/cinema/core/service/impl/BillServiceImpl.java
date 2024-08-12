package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.bill.Bill;
import com.nongviet201.cinema.core.entity.bill.TranslationPayment;
import com.nongviet201.cinema.core.entity.cinema.Showtime;
import com.nongviet201.cinema.core.entity.user.User;
import com.nongviet201.cinema.core.exception.BadRequestException;
import com.nongviet201.cinema.core.model.enums.PaymentMethod;
import com.nongviet201.cinema.core.repository.BillRepository;
import com.nongviet201.cinema.core.repository.TranslationPaymentRepository;
import com.nongviet201.cinema.core.request.BillRequestDTO;
import com.nongviet201.cinema.core.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BillServiceImpl implements BillService {
    private final UserService userService;
    private final ShowtimeService showtimeService;
    private final ReservationService reservationService;
    private final BillRepository billRepository;
    private final BillSeatService billSeatService;
    private final BillComboService billComboService;
    private final TranslationPaymentRepository translationPaymentRepository;

    @Override
    public Integer createBill(
        BillRequestDTO.PaymentRequest paymentRequest
    ) {
        User user = userService.getCurrentUser();

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
            .showTime(showtime)
            .totalPrice(0)
            .status(false)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
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

        TranslationPayment translationPayment = TranslationPayment.builder()
            .status(false)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .paymentMethod(PaymentMethod.values()[paymentRequest.getPaymentMethod()])
            .build();
        translationPaymentRepository.save(translationPayment);

        bill.setTotalPrice(totalPrice);
        bill.setTranslationPayment(translationPayment);
        billRepository.save(bill);

        return bill.getId();
    }

    @Override
    public Bill updateBill(Integer billId) {
        Bill bill = billRepository.findById(billId).orElse(null);
        assert bill != null;
        bill.setStatus(true);
        bill.setUpdatedAt(LocalDateTime.now());
        billRepository.save(bill);
        return bill;
    }

    @Override
    public Bill getBillById(Integer id) {
        return billRepository.findById(id).orElseThrow(
            () -> new BadRequestException("Không tìm thấy hóa đơn")
        );
    }
}
