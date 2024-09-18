package com.nongviet201.cinema.admin.sdk.request;

import lombok.Value;

public class UpsertTransactionRequest {

    @Value
    public static class GetDataFiller {
        String formDate;
        String toDate;
        Integer cinemaId;
    }
}
