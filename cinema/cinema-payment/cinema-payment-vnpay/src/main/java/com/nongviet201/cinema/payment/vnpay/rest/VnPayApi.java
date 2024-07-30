package com.nongviet201.cinema.payment.vnpay.rest;


import com.nongviet201.cinema.payment.vnpay.service.VnPayService;
import com.nongviet201.cinema.payment.vnpay.request.PaymentVnPayRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vnpay")
@AllArgsConstructor
public class VnPayApi {
    private final VnPayService vnPayService;

    @GetMapping("/create-payment")
    public ResponseEntity<?> createPayment(
        @Valid @RequestBody PaymentVnPayRequest request
    ) {
       return vnPayService.createPayment(request);
    }
}
