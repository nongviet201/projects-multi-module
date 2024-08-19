package com.nongviet201.cinema.web.sdk.controller.decorator;

import com.nongviet201.cinema.core.entity.Post;
import com.nongviet201.cinema.web.sdk.converter.WebPostToResponseConverter;
import com.nongviet201.cinema.web.sdk.response.WebPostResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebPostDecorator {

    private final WebPostToResponseConverter converter;

    public WebPostResponse decorate(
        Post post
    ) {
        return converter.convert(
            post.getId(),
            post.getTitle(),
            post.getThumbnail(),
            post.getContent(),
            post.getDescription(),
            post.getView(),
            post.getType().toString()
        );
    }
}
