package com.nongviet201.cinema.core.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nongviet201.cinema.core.security.CustomUserDetail;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthResponse {
    CustomUserDetail user;
    String accessToken;
    String refreshToken;

    @JsonProperty("isAuthenticated")
    Boolean isAuthenticated;
}