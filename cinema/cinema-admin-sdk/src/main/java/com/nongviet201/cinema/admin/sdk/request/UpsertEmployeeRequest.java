package com.nongviet201.cinema.admin.sdk.request;

import lombok.Value;

public class UpsertEmployeeRequest {

    @Value
    public static class EmployeeFilter {
        String formDate;
        String toDate;
        String type;
    }

    @Value
    public static class Find {
        String phoneNumber;
        Integer id;
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
