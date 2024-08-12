package com.nongviet201.cinema.payment.vnpay.code;

import lombok.Getter;

@Getter
public enum ResponseCodeVNPAY {
    PAYMENT_SUCCESS("00", "Giao dịch thành công"),
    PAYMENT_NOT_COMPLETE("01", "Giao dịch chưa hoàn tất"),
    PAYMENT_ERROR("02", "Giao dịch bị lỗi"),
    PAYMENT_DUPLICATE("04", "Giao dịch đảo (Khách hàng đã bị trừ tiền tại Ngân hàng nhưng GD chưa thành công ở VNPAY)"),
    PAYMENT_ERROR_CHEAT("07", "Trừ tiền thành công. Giao dịch bị nghi ngờ (liên quan tới lừa đảo, giao dịch bất thường)."),
    PAYMENT_TIME_OUT("11", "Giao dịch không thành công do: Đã hết hạn chờ thanh toán. Xin quý khách vui lòng thực hiện lại giao dịch."),
    PAYMENT_ERROR_OTP("13", "Giao dịch không thành công do Quý khách nhập sai mật khẩu xác thực giao dịch (OTP). Xin quý khách vui lòng thực hiện lại giao dịch. ");

    private final String code;
    private final String message;

    ResponseCodeVNPAY(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
