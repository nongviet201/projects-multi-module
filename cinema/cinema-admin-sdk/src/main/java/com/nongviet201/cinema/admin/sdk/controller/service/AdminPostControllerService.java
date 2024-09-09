package com.nongviet201.cinema.admin.sdk.controller.service;

import com.nongviet201.cinema.core.entity.Post;
import com.nongviet201.cinema.core.model.enums.PostType;
import com.nongviet201.cinema.core.service.PostService;
import com.nongviet201.cinema.core.utils.EnumService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminPostControllerService {

    private final PostService postService;
    private final EnumService enumService;

    public Post getPostById(
        Integer id
    ) {
        return postService.getPostById(id);
    }

    public List<PostType> getAllPostType() {
        return List.of(PostType.values());
    }
}
