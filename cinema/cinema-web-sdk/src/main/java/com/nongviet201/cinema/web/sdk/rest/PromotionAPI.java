package com.nongviet201.cinema.web.sdk.rest;

import com.nongviet201.cinema.core.service.UserStatisticService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/promotion")
public class PromotionAPI {

    private final UserStatisticService userStatisticService;

    @GetMapping("/check/{points}")
    public ResponseEntity<?> getReservations(
        @PathVariable Integer points
    ) {
        int userPoints = userStatisticService.getCurrentUserStatistic().getPoints();
        if(userPoints != 0) {
            if (points >= 20 && points <= 100) {
                if(userPoints >= points) {
                    return new ResponseEntity<>(true, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}
