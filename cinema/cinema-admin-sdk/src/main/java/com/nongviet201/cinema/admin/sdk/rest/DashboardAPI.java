package com.nongviet201.cinema.admin.sdk.rest;

import com.nongviet201.cinema.admin.sdk.controller.service.AdminDashboardControllerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/api/v1/admin-dashboard")
public class DashboardAPI {

    private final AdminDashboardControllerService dashboardControllerService;

    @GetMapping("/get/widget")
    public ResponseEntity<?> getWidget() {
        return new ResponseEntity<>(
            dashboardControllerService.getDashboardWidget(),
            HttpStatus.OK
        );
    }
}
