package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.exception.BadRequestException;
import com.nongviet201.cinema.core.model.entity.user.User;
import com.nongviet201.cinema.core.model.entity.bill.Bill;
import com.nongviet201.cinema.core.model.entity.cinema.Showtime;
import com.nongviet201.cinema.core.repository.BillRepository;
import com.nongviet201.cinema.core.repository.ShowtimeRepository;
import com.nongviet201.cinema.core.repository.UserRepository;

import com.nongviet201.cinema.core.request.BillRequestDTO;
import com.nongviet201.cinema.core.service.BillComboService;
import com.nongviet201.cinema.core.service.BillSeatService;
import com.nongviet201.cinema.core.service.BillService;
import com.nongviet201.cinema.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BillServiceImpl implements BillService {
    private final UserService userService;
    private final ShowtimeRepository showtimeRepository;
    private final BillRepository billRepository;
    private final BillSeatService billSeatService;
    private final BillComboService billComboService;

    @Override
    public Bill createBill(
        BillRequestDTO.PaymentRequest paymentRequest
    ) {
        User user = userService.getCurrentUser();

        Showtime showtime = showtimeRepository.findById(paymentRequest.getShowtimeId())
            .orElseThrow(() -> new BadRequestException("Suất chiếu không tồn tại"));;

        Bill bill = Bill.builder()
            .user(user)
            .showTime(showtime)
            .totalPrice(0)
            .status(false)
            .createAt(LocalDateTime.now())
            .updateAt(LocalDateTime.now())
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

        bill.setTotalPrice(totalPrice);
        billRepository.save(bill);
        return bill;
    }

    @Override
    public Bill updateBill(Integer billId) {
        Bill bill = billRepository.findById(billId).orElse(null);
        assert bill != null;
        bill.setStatus(true);
        bill.setUpdateAt(LocalDateTime.now());
        billRepository.save(bill);
        return bill;
    }

    @Override
    public Bill getBillById(Integer bill) {
        return billRepository.findById(bill).orElse(null);
    }
}
