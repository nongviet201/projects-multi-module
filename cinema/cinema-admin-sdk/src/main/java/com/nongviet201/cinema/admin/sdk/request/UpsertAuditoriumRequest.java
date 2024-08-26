package com.nongviet201.cinema.admin.sdk.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

public class UpsertAuditoriumRequest {

    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class AudUpdate {
        String name;
        String type;
        boolean enabled;
    }

    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class AudCreate {
        Integer cinemaId;
        String name;
        String type;
        boolean enabled;
        Integer totalRowChair;
        Integer totalColumnChair;
    }
}
