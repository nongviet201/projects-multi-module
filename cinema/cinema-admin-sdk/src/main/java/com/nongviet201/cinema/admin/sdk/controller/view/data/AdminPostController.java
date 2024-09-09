package com.nongviet201.cinema.admin.sdk.controller.view.data;

import com.nongviet201.cinema.admin.sdk.controller.service.AdminPostControllerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/post")
@AllArgsConstructor
public class AdminPostController {
    private final AdminPostControllerService adminPostControllerService;

    @GetMapping("")
    public String getPostListPage() {return "posts/index";}

    @GetMapping("/create")
    public String getCreatePostPage(
        Model model
    ) {
        model.addAttribute(
            "types",
            adminPostControllerService.getAllPostType()
        );
        return "/posts/create";
    }

    @GetMapping("/{id}")
    public String getEditPostPage(
        @PathVariable Integer id,
        Model model
    ) {
        model.addAttribute(
            "post",
            adminPostControllerService.getPostById(id)
        );
        model.addAttribute(
            "types",
            adminPostControllerService.getAllPostType()
        );
        return "posts/detail";
    }
}
