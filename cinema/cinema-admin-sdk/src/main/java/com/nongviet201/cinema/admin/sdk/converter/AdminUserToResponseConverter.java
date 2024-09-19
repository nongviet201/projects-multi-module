package com.nongviet201.cinema.admin.sdk.converter;

import com.nongviet201.cinema.admin.sdk.response.AdminUserResponse;
import com.nongviet201.cinema.core.model.enums.user.UserRank;
import org.springframework.stereotype.Service;

@Service
public class AdminUserToResponseConverter {

    public AdminUserResponse convert (
        String name,
        String email,
        String phoneNumber,
        String birthDay,
        boolean enabled,
        String genre,
        String createdAt,
        Integer points,
        Long totalSpending,
        UserRank userRank
    ) {
        return AdminUserResponse.builder()
            .name(name)
            .email(email)
            .phoneNumber(phoneNumber)
            .birthDay(birthDay)
            .enabled(enabled)
            .genre(genre)
            .createdAt(createdAt)
            .points(points)
            .totalSpending(totalSpending)
            .userRank(userRank)
            .build();
    }
}
