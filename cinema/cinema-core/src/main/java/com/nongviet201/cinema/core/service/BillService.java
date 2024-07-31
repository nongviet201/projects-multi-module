package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.model.entity.bill.Bill;

public interface BillService {
    Bill createBill(Integer userId, Integer showtimeId, Long TotalPrice);
    Bill updateBill(Integer billId);

    Bill getBillById(Integer bill);
}
