package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.bill.BillCombo;

import java.util.List;

public interface BillComboService {
    long createBillCombo(Integer billId, Integer ComboId, Integer quantity);

    List<BillCombo> getBIllComboByBillId(int billId);
}
