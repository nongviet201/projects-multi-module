package com.nongviet201.cinema.admin.sdk.rest;

import com.nongviet201.cinema.admin.sdk.request.UpsertTransactionRequest;
import com.nongviet201.cinema.admin.sdk.service.AdminTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

    @PostMapping("/export-excel")
    public ResponseEntity<?> exportExcelData(
        @RequestBody UpsertTransactionRequest.GetDataFiller request
    ) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=transactions.xlsx");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
            .headers(headers)
            .body(
                adminTransactionService.getExcelData(
                    request
                )
            );
    }
}
  