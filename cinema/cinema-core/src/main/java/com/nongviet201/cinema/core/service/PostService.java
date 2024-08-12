package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPublishPostsOrderByUpdatedAt();

    Post getPostById(Integer id);
}
