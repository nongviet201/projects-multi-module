package com.nongviet201.cinema.admin.sdk.converter;

import com.nongviet201.cinema.admin.sdk.response.AdminEmployeeResponse;
import org.springframework.stereotype.Service;

@Service
public class AdminEmployeeToResponseConverter {

    public AdminEmployeeResponse convert (
        int id,
        String name,
        String email,
        String phoneNumber,
        String genre,
        String birthDay,
        String position,
        String status,
        String joinDate
    ) {
        return AdminEmployeeResponse.builder()
            .id(id)
            .name(name)
            .email(email)
            .phoneNumber(phoneNumber)
            .genre(genre)
            .birthDay(birthDay)
            .position(position)
            .status(status)
            .joinDate(joinDate)
            .build();
    }
}
