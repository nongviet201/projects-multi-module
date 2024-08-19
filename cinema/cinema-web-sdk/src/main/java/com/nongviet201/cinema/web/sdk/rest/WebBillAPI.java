package com.nongviet201.cinema.web.sdk.rest;

import com.nongviet201.cinema.web.sdk.controller.service.WebUserBillProfileControllerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/web-bill")
public class WebBillAPI {

    private final WebUserBillProfileControllerService webUserBillProfileControllerService;

    @RequestMapping("/get/{billId}")
    public ResponseEntity<?> getSeat(
        @PathVariable int billId
    ) {
        return ResponseEntity.ok(
            webUserBillProfileControllerService.getBillDetailById(billId)
        );
    }
}
