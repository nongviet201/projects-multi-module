package com.nongviet201.cinema.admin.sdk.rest;

import com.nongviet201.cinema.admin.sdk.request.UpsertPostRequest;
import com.nongviet201.cinema.admin.sdk.service.AdminPostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/api/v1/post")
public class PostAPI {

    private final AdminPostService postService;

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePostStatus(
        @PathVariable Integer id,
        @RequestBody UpsertPostRequest request
    ) {
        postService.updatePost(
            id,
            request
        );
        return ResponseEntity.ok("Cập nhật bài viết thành công!");
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(
        @RequestBody UpsertPostRequest request
    ) {
        postService.createPost(request);
        return ResponseEntity.ok("Tạo bài viết thành công!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(
        @PathVariable Integer id
    ) {
        postService.deletePost(id);
        return ResponseEntity.ok("Xóa bài viết thành công!");
    }

    @GetMapping("/{type}")
    public ResponseEntity<?> getListPosts(
        @PathVariable String type
    ) {
        return ResponseEntity.ok(
            postService.getListPosts(type)
        );
    }
}
