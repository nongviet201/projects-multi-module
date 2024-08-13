package com.nongviet201.cinema.payment.vnpay.code;

import lombok.Getter;

@Getter
public enum ResponseCodeVNPAY {
    PAYMENT_SUCCESS("00", "Giao dịch thành công"),
    PAYMENT_NOT_COMPLETE("01", "Giao dịch chưa hoàn tất"),
    PAYMENT_ERROR("02", "Giao dịch bị lỗi"),
    PAYMENT_DUPLICATE("04", "Giao dịch đảo (Khách hàng đã bị trừ tiền tại Ngân hàng nhưng GD chưa thành công ở VNPAY)"),
    PAYMENT_ERROR_CHEAT("07", "Trừ tiền thành công. Giao dịch bị nghi ngờ (liên quan tới lừa đảo, giao dịch bất thường)."),
    PAYMENT_ERROR_AUTH("10", "Giao dịch không thành công do: Khách hàng xác thực thông tin thẻ/tài khoản không đúng quá 3 lần"),
    PAYMENT_TIME_OUT("11", "Giao dịch không thành công do: Đã hết hạn chờ thanh toán. Xin quý khách vui lòng thực hiện lại giao dịch."),
    PAYMENT_ERROR_OTP("13", "Giao dịch không thành công do Quý khách nhập sai mật khẩu xác thực giao dịch (OTP). Xin quý khách vui lòng thực hiện lại giao dịch."),
    PAYMENT_CANCEL("24", "Giao dịch không thành công do: Khách hàng hủy giao dịch"),
    PAYMENT_ERROR_NOT_ENOUGH_MONEY("51", "Giao dịch không thành công do: Tài khoản của quý khách không đủ số dư để thực hiện giao dịch."),
    PAYMENT_ERROR_ACCOUNT_OVER_LIMIT("65", "Giao dịch không thành công do: Tài khoản của Quý khách đã vượt quá hạn mức giao dịch trong ngày."),
    PAYMENT_ERROR_BANK_UNDER_MAINTENANCE("75", "Ngân hàng thanh toán đang bảo trì."),
    PAYMENT_ERROR_WRONG_PASSWORD("79", "Giao dịch không thành công do: KH nhập sai mật khẩu thanh toán quá số lần quy định. Xin quý khách vui lòng thực hiện lại giao dịch"),
    PAYMENT_UNKNOWN_ERROR("99", "Giao dịch không thành công do: Lỗi không xác định"),


    PAYMENT_ERROR_AMOUNT("1111", "Đơn hàng thanh toán thành công nhưng giá trị thanh toán VNPay trả về của đơn hàng không đúng với giá trị tồn tại trong cơ sở dữ liệu"),
    PAYMENT_ERROR_USER("1112", "Đơn hàng thanh toán thành công nhưng thông tin người dùng trong hóa đơn không phải người dùng hiện tại");


    private final String code;
    private final String message;

    ResponseCodeVNPAY(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // Phương thức tĩnh để lấy đối tượng enum dựa trên mã code
    public static ResponseCodeVNPAY fromCode(String code) {
        for (ResponseCodeVNPAY responseCode : values()) {
            if (responseCode.getCode().equals(code)) {
                return responseCode;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}
