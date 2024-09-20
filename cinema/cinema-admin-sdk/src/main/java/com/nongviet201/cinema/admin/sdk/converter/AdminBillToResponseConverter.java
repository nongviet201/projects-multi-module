package com.nongviet201.cinema.admin.sdk.converter;

import com.nongviet201.cinema.admin.sdk.response.AdminBillResponse;
import com.nongviet201.cinema.core.model.enums.cinema.SeatType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminBillToResponseConverter {

    public AdminBillResponse billConvert(
        String userName,
        String email,
        Integer billId,
        String payDate,
        String totalPrice,
        String discountPrice,
        String finalPrice,
        String code,
        String status,

        String showtimeDetail,
        List<AdminBillResponse.BillComboResponse> comboResponses,
        List<AdminBillResponse.BillSeatResponse> seatResponses
        ) {
        return AdminBillResponse.builder()
            .username(userName)
            .email(email)
            .billId(billId)
            .payDate(payDate)
            .totalPrice(totalPrice)
            .discountPrice(discountPrice)
            .finalPrice(finalPrice)
            .code(code)
            .status(status)
            .showtimeDetails(showtimeDetail)
            .comboResponses(comboResponses)
            .seatResponses(seatResponses)
            .build();
    }

    public AdminBillResponse.BillSeatResponse billSeatConvert(
        SeatType seatType,
        String name,
        int quantity,
        String price,
        String totalPrice
    ) {
        return AdminBillResponse.BillSeatResponse
            .builder()
            .seatType(seatType)
            .name(name)
            .quantity(quantity)
            .price(price)
            .totalPrice(totalPrice)
            .build();
    }

    public AdminBillResponse.BillComboResponse billComboConvert(
        String comboName,
        int quantity,
        String price,
        String totalPrice
    ) {
        return AdminBillResponse.BillComboResponse
           .builder()
           .name(comboName)
           .quantity(quantity)
            .price(price)
           .totalPrice(totalPrice)
           .build();
    }
}
