package com.nongviet201.cinema.admin.sdk.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

public class UpsertSeatRequest {
    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class seatUpdate {
        Integer[] seatIds;
        boolean status;
        boolean block;
        String type;
    }

}
