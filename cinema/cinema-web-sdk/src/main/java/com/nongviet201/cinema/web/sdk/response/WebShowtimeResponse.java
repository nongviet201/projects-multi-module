package com.nongviet201.cinema.web.sdk.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WebShowtimeResponse {
    private Integer id;
    private String screeningDate;
    private String startTime;
    private String movieName;
    private String moviePoster;
    private Integer ageRequirement;
    private Integer auditoriumId;
    private String auditoriumName;
    private String auditoriumType;
    private String cinemaName;
}
