package com.nongviet201.cinema.admin.sdk.request;

import lombok.Value;

public class UpsertUserRequest {

    @Value
    public static class UserFilter {
        String formDate;
        String toDate;
    }

    @Value
    public static class Find {
        String phoneNumber;
        String email;
    }

    @Value
    public static class Update {
        String oldEmail;
        String name;
        String email;
        String phoneNumber;
        String birthDay;
        String genre;
    }
}
