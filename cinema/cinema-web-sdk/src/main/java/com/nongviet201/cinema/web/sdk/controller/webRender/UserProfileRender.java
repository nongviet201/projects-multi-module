package com.nongviet201.cinema.web.sdk.controller.webRender;

import com.nongviet201.cinema.web.sdk.controller.service.WebUserControllerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/get")
@AllArgsConstructor
public class UserProfileRender {

    private final WebUserControllerService userControllerService;

    @GetMapping("/stage-1")
    public String getStageOneFragments(
        Model model
    ) {
        return "user/stage/stage-one";
    }

    @GetMapping("/stage-2")
    public String getStageTwoFragments(
        Model model
    ) {
        model.addAttribute(
            "user",
            userControllerService.getCurrentUser()
        );
        return "user/stage/stage-two";
    }
    @GetMapping("/stage-3")
    public String getStageThreeFragments(
        Model model
    ) {
        return "user/stage/stage-three";
    }
    @GetMapping("/stage-4")
    public String getStageFourFragments(
        Model model
    ) {
        return "user/stage/stage-four";
    }
    @GetMapping("/stage-5")
    public String getStageFiveFragments(
        Model model
    ) {
        return "user/stage/stage-five";
    }


}
