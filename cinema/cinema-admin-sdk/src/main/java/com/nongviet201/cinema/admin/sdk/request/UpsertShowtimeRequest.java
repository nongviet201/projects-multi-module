package com.nongviet201.cinema.admin.sdk.request;

import lombok.Value;

public class UpsertShowtimeRequest {

    @Value
    public static class GetDataFiller {
        String formDate;
        String toDate;
        Integer cinemaId;
        Integer movieId;
    }

    @Value
    public static class CreateShowtime {
        Integer auditoriumId;
        Integer movieScheduleId;
        String screeningDate;
        String startTime;
        String endTime;
        String screeningTimeType;
        String graphicsType;
        String translationType;
    }
}
