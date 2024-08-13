package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.bill.BillSeat;

import java.util.List;

public interface BillSeatService {
    long createBillSeat(Integer billId, Integer SeatId);

    List<BillSeat> getBillSeatByBillId(Integer billId);
}
