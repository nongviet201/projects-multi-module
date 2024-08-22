package com.nongviet201.cinema.admin.sdk.rest;

import com.nongviet201.cinema.admin.sdk.service.AdminRevenueService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.time.LocalDateTime.now;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/api/v1/revenue")
public class RevenueAPI {

    private final AdminRevenueService adminRevenueService;

    @GetMapping("/get/all-total-on-month-for-year")
    public ResponseEntity<?> getTotalRevenueOnMonth(
        @RequestParam(value = ("year"), required = false) Integer year
    ) {
        if (year == null) {
            year = now().getYear();
        }
        return new ResponseEntity<>(
            adminRevenueService.getTotalMonthlyRevenueOfAllCinemasForYear(year),
            HttpStatus.OK
        );
    }
}
