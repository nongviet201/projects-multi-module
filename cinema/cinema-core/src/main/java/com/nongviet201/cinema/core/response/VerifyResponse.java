package com.nongviet201.cinema.core.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerifyResponse {
    Boolean success;
    String message;
    String token;

    public boolean isSuccess() {
        return success;
    }
}
