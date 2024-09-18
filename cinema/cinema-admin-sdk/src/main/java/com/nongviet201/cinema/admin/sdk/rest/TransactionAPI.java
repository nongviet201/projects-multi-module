package com.nongviet201.cinema.admin.sdk.rest;

import com.nongviet201.cinema.admin.sdk.request.UpsertTransactionRequest;
import com.nongviet201.cinema.admin.sdk.service.AdminTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1/transaction")
@AllArgsConstructor
public class TransactionAPI {

    private final AdminTransactionService adminTransactionService;

    @PostMapping("/getData")
    public ResponseEntity<?> getTransactionData(
        @RequestBody UpsertTransactionRequest.GetDataFiller request
    ) {
        return ResponseEntity.ok(
            adminTransactionService.getTransactionFilter(request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionData(
        @PathVariable Integer id
    ) {
        return ResponseEntity.ok(
            adminTransactionService.getTransactionById(id)
        );
    }
}
  