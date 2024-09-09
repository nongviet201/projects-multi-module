package com.nongviet201.cinema.admin.sdk.request;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpsertPostRequest {
    String title;
    String content;
    String description;
    String type;
    String slug;
    String thumbnail;
    boolean status;
}
