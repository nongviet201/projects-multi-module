package com.nongviet201.cinema.admin.sdk.rest;

import com.nongviet201.cinema.admin.sdk.request.UpsertAuditoriumRequest;
import com.nongviet201.cinema.admin.sdk.service.AdminAuditoriumService;
import com.nongviet201.cinema.admin.sdk.service.AdminCinemaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/api/v1/aud")
public class AuditoriumAPI {

    private final AdminAuditoriumService adminAuditoriumService;

    @PostMapping("")
    public ResponseEntity<?> createAud(
        @RequestBody UpsertAuditoriumRequest.AudCreate request
    ) {
        adminAuditoriumService.createAud(request);
        return ResponseEntity.ok(request);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAud(
        @PathVariable int id,
        @RequestBody UpsertAuditoriumRequest.AudUpdate request
    ) {
        adminAuditoriumService.updateAud(id, request);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAud(
        @PathVariable int id
    ) {
        adminAuditoriumService.updateDeletedAud(id, true);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/restore/{id}")
    public ResponseEntity<?> restoreAud(
        @PathVariable int id
    ) {
        adminAuditoriumService.updateDeletedAud(id, false);
        return ResponseEntity.noContent().build();
    }
}
