package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.model.entity.User;
import com.nongviet201.cinema.core.model.entity.bill.Bill;
import com.nongviet201.cinema.core.model.entity.cinema.Showtime;
import com.nongviet201.cinema.core.repository.BillRepository;
import com.nongviet201.cinema.core.repository.ShowtimeRepository;
import com.nongviet201.cinema.core.repository.UserRepository;

import com.nongviet201.cinema.core.service.BillService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BillServiceImpl implements BillService {
    private final UserRepository userRepository;
    private final ShowtimeRepository showtimeRepository;
    private final BillRepository billRepository;
    @Override
    public Bill createBill(
        Integer userId,
        Integer showtimeId,
        Long totalPrice) {
        User user = userRepository.findById(userId).orElse(null);
        Showtime showtime = showtimeRepository.findById(showtimeId).orElse(null);

        Bill bill = Bill.builder()
            .user(user)
            .showTime(showtime)
            .totalPrice(totalPrice)
            .status(false)
            .createAt(LocalDateTime.now())
            .updateAt(LocalDateTime.now())
            .build();

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
