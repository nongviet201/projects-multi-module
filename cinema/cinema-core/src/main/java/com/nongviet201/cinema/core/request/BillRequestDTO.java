package com.nongviet201.cinema.core.request;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
