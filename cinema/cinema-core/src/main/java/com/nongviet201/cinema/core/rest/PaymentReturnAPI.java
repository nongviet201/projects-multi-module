package com.nongviet201.cinema.core.rest;

import com.nongviet201.cinema.core.request.VnPayReturnRequest;
import com.nongviet201.cinema.core.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/v1/payment")
@AllArgsConstructor
public class PaymentReturnAPI {

    private final PaymentService paymentService;

    @GetMapping("/vnpay-return")
    public String vnpayReturn(
        @RequestParam(value = "vnp_TxnRef") Integer billId,
        @RequestParam(value = "vnp_ResponseCode") String responseCode,
        @RequestParam(value = "vnp_BankCode") String bankCode,
        @RequestParam(value = "vnp_TransactionNo") String transactionNo,
        @RequestParam(value = "vnp_TransactionStatus") String transactionStatus,
        @RequestParam(value = "vnp_PayDate") String payDate,
        @RequestParam(value = "vnp_Amount") Long amount
    ){
        Integer id = paymentService.PaymentVnPayReturnCheck(
            VnPayReturnRequest.builder()
                .transitionId(billId)
                .responseCode(responseCode)
                .transactionNo(transactionNo)
                .transactionStatus(transactionStatus)
                .bankCode(bankCode)
                .amount(amount)
                .payDate(payDate)
                .build()
        );

        return "redirect:/booking?billId=" + id;
    }
}
