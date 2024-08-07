package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.model.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPublishPostsOrderByUpdatedAt();
}
