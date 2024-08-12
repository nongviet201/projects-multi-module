package com.nongviet201.cinema.core.rest;

import com.nongviet201.cinema.core.entity.bill.Bill;
import com.nongviet201.cinema.core.request.BillRequestDTO;
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

    @PostMapping("/create")
    protected ResponseEntity<?> createBill(
        @RequestBody BillRequestDTO.PaymentRequest paymentRequest
    ) {
        return ResponseEntity.ok(
            billService.createBill(paymentRequest)
        );
    }

    @RequestMapping("/update")
    protected ResponseEntity<?> updateBill(
        @Valid @RequestBody Integer billId
    ) {
        Bill bill = billService.updateBill(billId);
        return ResponseEntity.ok(bill.getId());
    }
}
