package com.nongviet201.cinema.web.sdk.request;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BillRequestDTO {
    @Getter
    @Setter
    public static class BillRequest {
        Integer userId;
        Integer showtimeId;
        long totalPrice;
    }
    @Getter
    @Setter
    public static class ComboRequest {
        Integer comboId;
        Integer quantity;
    }

    @Getter
    @Setter
    public static class PaymentRequest {
        private BillRequest billRequest;
        private List<ComboRequest> comboRequest;
        private List<Integer> seatRequest;
    }
}
