package com.nongviet201.cinema.admin.sdk.rest;

import com.nongviet201.cinema.admin.sdk.request.UpsertUserRequest;
import com.nongviet201.cinema.admin.sdk.service.AdminUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1/user")
@AllArgsConstructor
public class AdminUserAPI {

    private final AdminUserService adminUserService;

    @PostMapping("/getData")
    public ResponseEntity<?> getData (
        @RequestBody UpsertUserRequest.UserFilter request
    ) {
        return ResponseEntity.ok(
            adminUserService.getDataFilter(request)
        );
    }

    @PostMapping("/findUser")
    public ResponseEntity<?> findUser (
        @RequestBody UpsertUserRequest.Find request
    ) {
        return ResponseEntity.ok(
            adminUserService.findUser(request)
        );
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser (
        @RequestBody UpsertUserRequest.Update request
    ) {
        adminUserService.updateUser(request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/sendToken")
    public ResponseEntity<?> sendToken (
        @RequestParam String email,
        @RequestParam String type
    ) {
        adminUserService.sendTokenConfirm(
            email,
            type
        );
        return ResponseEntity.noContent().build();
    }

}
