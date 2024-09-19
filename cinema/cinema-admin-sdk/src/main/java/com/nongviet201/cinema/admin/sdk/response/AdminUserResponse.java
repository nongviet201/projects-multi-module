package com.nongviet201.cinema.admin.sdk.response;

import com.nongviet201.cinema.core.model.enums.user.UserRank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AdminUserResponse {
    private String name;
    private String email;
    private String phoneNumber;
    private String birthDay;
    private boolean enabled;
    private String genre;
    private String createdAt;
    private Integer points;
    private Long totalSpending;
    private UserRank userRank;
}
