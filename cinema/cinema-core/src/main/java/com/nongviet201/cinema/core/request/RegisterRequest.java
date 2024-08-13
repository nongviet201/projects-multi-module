package com.nongviet201.cinema.core.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    protected String fullName;
    protected String email;
    protected String phoneNumber;
    protected String gender;
    protected String birthday;
    protected String password;
    protected String confirmPassword;
}
