package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.bill.Bill;
import com.nongviet201.cinema.core.request.BillRequestDTO;

import java.util.List;

public interface BillService {
    String createBill(BillRequestDTO.PaymentRequest paymentRequest);
    Bill getBillById(Integer bill);

    List<Bill> clientGetBillUserProfile();
}
