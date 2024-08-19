package com.nongviet201.cinema.web.sdk.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WebPostResponse {
    private Integer id;
    private String title;
    private String description;
    private String thumbnail;
    private Integer view;
    private String content;
    private String type;
}
