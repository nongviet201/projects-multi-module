package com.nongviet201.cinema.admin.sdk.response;

import com.nongviet201.cinema.core.model.enums.cinema.SeatType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AdminBillResponse {
    private String username;
    private String email;
    private Integer billId;
    private String payDate;
    private String totalPrice;
    private String discountPrice;
    private String finalPrice;
    private String code;
    private String status;

    private String showtimeDetails;
    List<BillComboResponse> comboResponses;
    List<BillSeatResponse> seatResponses;


    @Getter
    @Builder
    public static class BillSeatResponse {
        private SeatType seatType;
        private String name;
        private int quantity;
        private String price;
        private String totalPrice;
    }
    @Getter
    @Builder
    public static class BillComboResponse {
        private String name;
        private int quantity;
        private String price;
        private String totalPrice;
    }
}
