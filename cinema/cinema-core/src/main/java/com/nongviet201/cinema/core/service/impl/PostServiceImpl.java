package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.model.entity.Post;
import com.nongviet201.cinema.core.repository.PostRepository;
import com.nongviet201.cinema.core.service.PostService;
import lombok.AllArgsConstructor;
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
}
