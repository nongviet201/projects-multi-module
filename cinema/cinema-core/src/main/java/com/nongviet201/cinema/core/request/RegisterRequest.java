package com.nongviet201.cinema.core.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    String fullName;
    String email;
    String phoneNumber;
    String gender;
    String birthday;
    String password;
    String confirmPassword;
}
