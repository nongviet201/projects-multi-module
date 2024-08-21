package com.nongviet201.cinema.admin.sdk.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminCinemaMarkerResponse {
    private Integer id;
    private String name;
    private Double lat;
    private Double lng;
    private String address;
}
