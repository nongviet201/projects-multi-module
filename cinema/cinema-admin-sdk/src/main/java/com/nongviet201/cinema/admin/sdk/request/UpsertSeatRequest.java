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
    public static class SeatUpdate {
        Integer[] seatIds;
        boolean status;
        String type;
    }

    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class SeatCreate {
        Integer seatId;
        Integer audId;
        String seatRow;
        Integer startColumn;
        Integer endColumn;
        Integer positions;
    }

    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class CreateRowAndColumn {
        Integer audId;
        Integer newRow;
        Integer newColumn;
    }
}
