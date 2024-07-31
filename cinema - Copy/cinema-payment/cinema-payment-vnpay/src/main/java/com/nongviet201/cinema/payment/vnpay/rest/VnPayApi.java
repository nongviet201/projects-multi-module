package com.nongviet201.cinema.payment.vnpay.rest;


import com.nongviet201.cinema.payment.vnpay.service.VnPayService;
import com.nongviet201.cinema.payment.vnpay.request.PaymentVnPayRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vnpay")
@AllArgsConstructor
public class VnPayApi {
    private final VnPayService vnPayService;

    @PostMapping("/create-payment")
    public ResponseEntity<?> createPayment(
        @RequestBody PaymentVnPayRequest request
    ) {
       return vnPayService.createPayment(request);
    }
}
