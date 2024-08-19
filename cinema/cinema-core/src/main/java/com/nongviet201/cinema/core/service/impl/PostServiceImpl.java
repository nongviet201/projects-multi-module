package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.Post;
import com.nongviet201.cinema.core.model.enums.PostType;
import com.nongviet201.cinema.core.repository.PostRepository;
import com.nongviet201.cinema.core.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public List<Post> getAllPublishPostsOrderByUpdatedAt() {
        return postRepository.findAllByStatusOrderByCreatedAt(true);
    }

    @Override
    public Post getPostById(Integer id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Post> getAllPublishPostsByTypeOrderByReleaseDate(
        PostType type,
        int page,
        int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findByTypeAndStatusOrderByPublishedAt(
            type,
            true,
            pageable
        );
    }

    @Override
    public void updateViewCount(int postId) {
        Post post = getPostById(postId);
        post.setView(post.getView() + 1);
        postRepository.save(post);
    }

}
