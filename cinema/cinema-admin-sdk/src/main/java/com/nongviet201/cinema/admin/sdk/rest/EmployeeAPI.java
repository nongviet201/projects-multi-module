package com.nongviet201.cinema.admin.sdk.rest;

import com.nongviet201.cinema.admin.sdk.request.UpsertEmployeeRequest;
import com.nongviet201.cinema.admin.sdk.service.AdminEmployeesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1/employee")
@AllArgsConstructor
public class EmployeeAPI {

    private final AdminEmployeesService adminEmployeesService;

    @PostMapping("/getData")
    public ResponseEntity<?> getAllEmployees(
        @RequestBody UpsertEmployeeRequest.EmployeeFilter request
    ) {
        return ResponseEntity.ok(
            adminEmployeesService.getEmployeesDataFilter(request)
        );
    }

    @PostMapping("/find")
    public ResponseEntity<?> getAllEmployees(
        @RequestBody UpsertEmployeeRequest.Find request
    ) {
        return ResponseEntity.ok(
            adminEmployeesService.getEmployeeFindByData(request)
        );
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(

    ){
        return ResponseEntity.ok("ok");
    }
}
