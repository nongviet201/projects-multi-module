package com.nongviet201.cinema.core.model.enums.bill;

import lombok.Getter;

@Getter
public enum BillStatus {
    CANCEL("Đơn Hàng Bị Hủy"),
    PAID("Đã Thanh Toán"),
    FAILED("Thanh Toán Thất Bại"),
    PENDING_PAYMENT("Chờ Thanh Toán"),
    PENDING_PROCESSING("Đơn Hàng Đang Được Xử Lý");

    private final String message;

    BillStatus(String message) {
        this.message = message;
    }
}
