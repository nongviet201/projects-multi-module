package com.nongviet201.cinema.admin.sdk.request;

import lombok.*;
import lombok.experimental.FieldDefaults;


public class UpsertCinemaRequest {

    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Cinema {
        String name;
        String address;
        boolean enabled;
        Integer city;
        Double lat;
        Double lng;
    }

}