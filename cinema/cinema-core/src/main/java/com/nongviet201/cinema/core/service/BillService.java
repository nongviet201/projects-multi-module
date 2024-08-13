package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.bill.Bill;
import com.nongviet201.cinema.core.request.BillRequestDTO;

public interface BillService {
    String createBill(BillRequestDTO.PaymentRequest paymentRequest);
    Bill updateBill(Integer billId);
    Bill getBillById(Integer bill);
}
