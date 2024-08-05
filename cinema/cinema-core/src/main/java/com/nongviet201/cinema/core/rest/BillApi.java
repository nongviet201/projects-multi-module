package com.nongviet201.cinema.core.rest;

import com.nongviet201.cinema.core.model.entity.bill.Bill;
import com.nongviet201.cinema.core.request.BillRequestDTO;
import com.nongviet201.cinema.core.service.BillComboService;
import com.nongviet201.cinema.core.service.BillSeatService;
import com.nongviet201.cinema.core.service.BillService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/bills")
public class BillApi {
    private final BillService billService;
    private final BillSeatService billSeatService;
    private final BillComboService billComboService;

    @PostMapping("/create")
    protected ResponseEntity<?> createBill(@RequestBody BillRequestDTO.PaymentRequest paymentRequest) {
        Bill bill = billService.createBill(
            paymentRequest.getBillRequest().getUserId(),
            paymentRequest.getBillRequest().getShowtimeId(),
            paymentRequest.getBillRequest().getTotalPrice()
        );

        for (BillRequestDTO.ComboRequest combo : paymentRequest.getComboRequest()) {
            billComboService.createBillCombo(
                bill.getId(),
                combo.getComboId(),
                combo.getQuantity()
            );
        }

        for (int seatId : paymentRequest.getSeatRequest()) {
            billSeatService.createBillSeat(
                bill.getId(),
                seatId
            );
        }
        return ResponseEntity.ok(bill.getId());
    }


    @RequestMapping("/update")
    protected ResponseEntity<?> updateBill(
        @Valid @RequestBody Integer billId
    ) {
        Bill bill = billService.updateBill(billId);
        return ResponseEntity.ok(bill.getId());
    }
}
