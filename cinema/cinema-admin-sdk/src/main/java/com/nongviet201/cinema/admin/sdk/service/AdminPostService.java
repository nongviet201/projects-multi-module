package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.admin.sdk.request.UpsertPostRequest;
import com.nongviet201.cinema.core.entity.Post;
import com.nongviet201.cinema.core.model.enums.PostType;
import com.nongviet201.cinema.core.service.PostService;
import com.nongviet201.cinema.core.service.UserService;
import com.nongviet201.cinema.core.utils.EnumService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminPostService {

    private final PostService postService;
    private final EnumService enumService;
    private final UserService userService;

    public void updatePost(
        Integer id,
        UpsertPostRequest request
    ) {
        Post post = postService.getPostById(id);
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setDescription(request.getDescription());
        post.setSlug(request.getSlug());
        post.setThumbnail(request.getThumbnail());
        post.setStatus(request.isStatus());
        post.setUpdatedAt(LocalDate.now());
        post.setType(enumService.getEnumValueByName(PostType::valueOf, request.getType(), "PostType"));
        if(request.isStatus()) {
            post.setPublishedAt(LocalDate.now());
        }
        postService.save(post);
    }

    public void createPost(
        UpsertPostRequest request
    ) {
        postService.save(
            Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .description(request.getDescription())
                .slug(request.getSlug())
                .thumbnail(request.getThumbnail())
                .status(request.isStatus())
                .publishedAt(request.isStatus()? LocalDate.now() : null)
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .type(enumService.getEnumValueByName(PostType::valueOf, request.getType(), "PostType"))
                .view(0)
                .user(userService.getCurrentUser())
                .build()
        );
    }

    public void deletePost(
        Integer id
    ) {
        postService.deleteById(id);
    }

    public List<Post> getListPosts(
        String type
    ) {
        if (type.equals("own")) {
            return postService.getAllOwnPostOrderByCreatedAt();
        }
        return postService.getAllPosts();
    }
}
