package com.nongviet201.cinema.admin.sdk.request;


import lombok.Value;

@Value
public class UpsertBlockRequest {
    Integer blockId;
    Integer audId;
    String seatRow;
    Integer startColumn;
    Integer endColumn;
    Integer positions;
}
