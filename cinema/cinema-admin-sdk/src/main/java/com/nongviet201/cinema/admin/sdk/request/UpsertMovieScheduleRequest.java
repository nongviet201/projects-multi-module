package com.nongviet201.cinema.admin.sdk.request;

import lombok.Value;

import java.time.LocalDate;

public class UpsertMovieScheduleRequest {

    @Value
    public static class CreateAndUpdate {
        Integer movieId;
        LocalDate startDate;
        LocalDate endDate;
        String status;
    }

    @Value
    public static class GetMovieScheduleFiller {
        String formDate;
        String toDate;
    }
}
