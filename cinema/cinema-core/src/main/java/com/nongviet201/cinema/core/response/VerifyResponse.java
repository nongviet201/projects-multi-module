package com.nongviet201.cinema.core.response;

import com.nongviet201.cinema.core.model.enums.bill.VerifyResponseType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerifyResponse {
    private VerifyResponseType type;
    private Boolean status;
    private String message;
    private String token;
}
