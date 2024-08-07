package com.nongviet201.cinema.web.sdk.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WebUserResponse {
    private String email;
    private String fullName;
    private String phoneNumber;
    private String avatar;
    private String birthday;
    private String gender;
}
