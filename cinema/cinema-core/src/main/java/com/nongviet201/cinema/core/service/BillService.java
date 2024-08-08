package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.model.entity.bill.Bill;
import com.nongviet201.cinema.core.request.BillRequestDTO;

public interface BillService {
    Bill createBill(BillRequestDTO.PaymentRequest paymentRequest);
    Bill updateBill(Integer billId);

    Bill getBillById(Integer bill);
}
