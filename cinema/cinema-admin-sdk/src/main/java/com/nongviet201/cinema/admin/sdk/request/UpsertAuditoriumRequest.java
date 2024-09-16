package com.nongviet201.cinema.admin.sdk.request;

import lombok.Value;

public class UpsertAuditoriumRequest {

    @Value
    public static class AudUpdate {
        String name;
        String type;
        boolean enabled;
    }

    @Value
    public static class AudCreate {
        Integer cinemaId;
        String name;
        String type;
        boolean enabled;
        Integer totalRowChair;
        Integer totalColumnChair;
    }
}
