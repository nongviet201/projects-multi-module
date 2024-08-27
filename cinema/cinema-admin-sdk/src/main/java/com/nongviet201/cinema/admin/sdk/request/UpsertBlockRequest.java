package com.nongviet201.cinema.admin.sdk.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpsertBlockRequest {
    Integer audId;
    String seatRow;
    Integer startColumn;
    Integer endColumn;
    Integer positions;
}
