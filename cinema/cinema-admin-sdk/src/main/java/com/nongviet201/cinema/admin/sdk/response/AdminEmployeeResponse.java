package com.nongviet201.cinema.admin.sdk.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminEmployeeResponse {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private String genre;
    private String birthDay;
    private String position;
    private String status;
    private String joinDate;
}
