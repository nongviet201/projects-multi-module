package com.nongviet201.cinema.web.sdk.rest;

import com.nongviet201.cinema.web.sdk.service.WebBlockService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/block")
@AllArgsConstructor
public class BlockAPI {

    private final WebBlockService webBlockService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBlockByAuditoriumId(
        @PathVariable Integer id
    ) {
        return ResponseEntity.ok(
            webBlockService.getBlocksByAuditoriumId(id)
        );
    }
}
