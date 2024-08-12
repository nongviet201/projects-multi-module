package com.nongviet201.cinema.web.sdk.converter;

import com.nongviet201.cinema.web.sdk.response.WebUserResponse;
import org.springframework.stereotype.Service;

@Service
public class WebUserToResponseConverter {

    public WebUserResponse convert(
        String email,
        String fullName,
        String phoneNumber,
        String avatar,
        String gender,
        String birthday
    ) {
        return WebUserResponse.builder()
            .email(email)
            .fullName(fullName)
            .phoneNumber(phoneNumber)
            .avatar(avatar)
            .gender(gender)
            .birthday(birthday)
            .build();
    }
}
