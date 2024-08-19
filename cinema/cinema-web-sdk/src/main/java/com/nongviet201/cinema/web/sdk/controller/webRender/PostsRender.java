package com.nongviet201.cinema.web.sdk.controller.webRender;

import com.nongviet201.cinema.core.model.enums.PostType;
import com.nongviet201.cinema.web.sdk.controller.service.WebPostsControllerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class PostsRender {

    private final WebPostsControllerService webPostsControllerService;

    @GetMapping("/get")
    public String getBlogPostsContent(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(value = "type", required = false, defaultValue = "blog") String type,
        Model model
    ) {
        if(type!= null) {
            switch (type) {
                case "blog":
                    model.addAttribute(
                        "posts",
                        webPostsControllerService.getPostsByTypePage(PostType.BLOG, page, size)
                    );
                    return "post/content/blog";
                case "review":
                    model.addAttribute(
                        "posts",
                        webPostsControllerService.getPostsByTypePage(PostType.REVIEW, page, size)
                    );
                    return "post/content/review";
                case "events":
                    model.addAttribute(
                        "posts",
                        webPostsControllerService.getPostsByTypePage(PostType.EVENT, page, size)
                    );
                    return "post/content/events";
            }
        }
        return "post/content/blog";
    }
}
