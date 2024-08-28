package com.nongviet201.cinema.admin.sdk.rest;

import com.nongviet201.cinema.admin.sdk.request.UpsertBlockRequest;
import com.nongviet201.cinema.admin.sdk.service.AdminBlockService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/api/v1/block")
public class BlockAPI {

    private final AdminBlockService adminBlockService;

    @GetMapping("/aud/{id}")
    public ResponseEntity<?> getAllBlockByAudId(
        @PathVariable Integer id
    ) {
        return new ResponseEntity<>(
            adminBlockService.getAllBlockByAuditoriumId(id),
            HttpStatus.OK
        );
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBlock(
        @RequestBody List<UpsertBlockRequest> request
        ) {
        adminBlockService.createBlock(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBlock(
        @RequestBody List<UpsertBlockRequest> request
    ) {
        adminBlockService.updateBlock(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deleteBlock(
        @PathVariable List<Integer> ids
    ) {
        adminBlockService.deleteBlockByListId(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
