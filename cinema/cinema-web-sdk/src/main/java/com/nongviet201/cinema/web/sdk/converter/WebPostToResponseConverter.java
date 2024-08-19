package com.nongviet201.cinema.web.sdk.converter;

import com.nongviet201.cinema.web.sdk.response.WebPostResponse;
import org.springframework.stereotype.Service;

@Service
public class WebPostToResponseConverter {

    public WebPostResponse convert(
        int id,
        String title,
        String thumbnail,
        String content,
        String description,
        int view,
        String type
    ) {
        return WebPostResponse.builder()
            .id(id)
            .title(title)
            .thumbnail(thumbnail)
            .content(content)
            .description(description)
            .view(view)
            .type(type)
            .build();
    }
}
