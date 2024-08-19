package com.nongviet201.cinema.web.sdk.controller.service;

import com.nongviet201.cinema.core.entity.Post;
import com.nongviet201.cinema.core.model.enums.PostType;
import com.nongviet201.cinema.core.service.PostService;
import com.nongviet201.cinema.web.sdk.controller.decorator.WebPostDecorator;
import com.nongviet201.cinema.web.sdk.response.WebPostResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebPostsControllerService {

    private final PostService postService;
    private final WebPostDecorator decorator;

    public Page<WebPostResponse> getPostsByTypePage(
        PostType type,
        int page,
        int size
    ) {
        return postService.getAllPublishPostsByTypeOrderByReleaseDate(
            type,
            page,
            size
        ).map(decorator::decorate);
    }

    public WebPostResponse getPostsById(
        int id
    ) {
        return decorator.decorate(postService.getPostById(id));
    }
}

