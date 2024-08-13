package com.nongviet201.cinema.core.request;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillRequestDTO {
    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ComboRequest {
        Integer comboId;
        Integer quantity;
    }

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class PaymentRequest {
        Integer showtimeId;
        List<ComboRequest> comboRequest;
        List<Integer> seatRequest;
        Integer paymentMethod;
    }
}
