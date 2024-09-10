package com.nongviet201.cinema.admin.sdk.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpsertMovieScheduleRequest {
    Integer movieId;
    LocalDate startDate;
    LocalDate endDate;
    String status;
}
