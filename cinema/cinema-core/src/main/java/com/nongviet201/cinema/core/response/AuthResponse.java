package com.nongviet201.cinema.core.response;

import lombok.*;

@Builder
@Getter
public class AuthResponse {
    public String avatar;
    public String fullName;
}