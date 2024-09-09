package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.Post;
import com.nongviet201.cinema.core.model.enums.PostType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    List<Post> getAllPublishPostsOrderByUpdatedAt();

    Post getPostById(Integer id);

    Page<Post> getAllPublishPostsByTypeOrderByReleaseDate(PostType type, int page, int size);

    void updateViewCount(int postId);

    List<Post> getAllOwnPostOrderByCreatedAt();

    List<Post> getAllPosts();

    void save(Post post);

    void deleteById(Integer id);
}