package com.nongviet201.cinema.admin.sdk.converter;

import com.nongviet201.cinema.admin.sdk.response.AdminTransactionResponse;
import com.nongviet201.cinema.core.model.enums.bill.PaymentMethod;
import org.springframework.stereotype.Service;

@Service
public class AdminTransactionToResponseConverter {

    public AdminTransactionResponse convert(
        int id,
        String transactionNo,
        String bankCode,
        PaymentMethod paymentMethod,
        int cinemaId,
        String cinemaName,
        String payDate,
        String status,
        String userName,
        String phoneNumber,
        String movieName,
        String screeningDateTime,
        Long totalPrice,
        Long discountAmount,
        Integer points
    ) {
        return AdminTransactionResponse.builder()
            .id(id)
            .transactionNo(transactionNo)
            .bankCode(bankCode)
            .paymentMethod(paymentMethod)
            .cinemaId(cinemaId)
            .cinemaName(cinemaName)
            .payDate(payDate)
            .status(status)
            .userName(userName)
            .phoneNumber(phoneNumber)
            .movieName(movieName)
            .screeningDateTime(screeningDateTime)
            .totalPrice(totalPrice)
            .discountAmount(discountAmount)
            .points(points)
            .build();
    }
}
