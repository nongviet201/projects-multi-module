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
    public static class ComboRequest {
        Integer comboId;
        Integer quantity;
    }

    @Getter
    @Setter
    public static class PaymentRequest {
        private Integer showtimeId;
        private List<ComboRequest> comboRequest;
        private List<Integer> seatRequest;
        private Integer paymentMethod;
    }
}
