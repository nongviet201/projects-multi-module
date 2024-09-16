package com.nongviet201.cinema.admin.sdk.request;

import lombok.Value;

@Value
public class UpsertPostRequest {
    String title;
    String content;
    String description;
    String type;
    String slug;
    String thumbnail;
    boolean status;
}
