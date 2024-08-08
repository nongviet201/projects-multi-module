package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.exception.BadRequestException;
import com.nongviet201.cinema.core.model.entity.bill.Bill;
import com.nongviet201.cinema.core.model.entity.bill.BillCombo;
import com.nongviet201.cinema.core.model.entity.bill.Combo;
import com.nongviet201.cinema.core.repository.BillComboRepository;
import com.nongviet201.cinema.core.repository.BillRepository;
import com.nongviet201.cinema.core.repository.ComboRepository;
import com.nongviet201.cinema.core.service.BillComboService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BillComboServiceImpl implements BillComboService {
    private final BillComboRepository billComboRepository;
    private final BillRepository billRepository;
    private final ComboRepository comboRepository;

    @Override
    public long createBillCombo(Integer billId, Integer comboId, Integer quantity) {
        Combo combo = comboRepository.findById(comboId)
            .orElseThrow(() -> new BadRequestException("Combo không tồn tại"));

        Bill bill = billRepository.findById(billId)
            .orElseThrow(() -> new BadRequestException("Bill không tồn tại"));

        assert combo != null;
        billComboRepository.save(
            BillCombo.builder()
                .bill(bill)
                .combo(combo)
                .quantity(quantity)
                .price(combo.getPrice() * quantity)
                .build()
        );

        return combo.getPrice() * quantity;
    }
}
