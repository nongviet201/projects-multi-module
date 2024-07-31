package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.model.entity.bill.Bill;
import com.nongviet201.cinema.core.model.entity.bill.BillSeat;
import com.nongviet201.cinema.core.model.entity.cinema.Seat;
import com.nongviet201.cinema.core.repository.BillRepository;
import com.nongviet201.cinema.core.repository.BillSeatRepository;
import com.nongviet201.cinema.core.repository.SeatRepository;
import com.nongviet201.cinema.core.service.BillSeatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BillSeatServiceImpl implements BillSeatService {
    private final BillRepository billRepository;
    private final SeatRepository seatRepository;
    private final BillSeatRepository billSeatRepository;
    @Override
    public void createBillSeat(Integer billId, Integer SeatId) {
        Bill bill = billRepository.findById(billId).orElse(null);
        Seat seat = seatRepository.findById(SeatId).orElse(null);
        assert seat != null;

        billSeatRepository.save(
            BillSeat.builder()
                .bill(bill)
                .seat(seat)
                .price(seat.getType().getPrice())
                .build()
        );
    }
}
