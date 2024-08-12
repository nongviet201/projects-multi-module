package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.bill.BillSeat;

public interface BillSeatService {
    long createBillSeat(Integer billId, Integer SeatId);

    BillSeat getBillSeatByBillId(Integer billId);
}
