package com.nongviet201.cinema.admin.sdk.service;

import com.nongviet201.cinema.admin.sdk.decorator.AdminEmployeeDecorator;
import com.nongviet201.cinema.admin.sdk.request.UpsertEmployeeRequest;
import com.nongviet201.cinema.admin.sdk.response.AdminEmployeeResponse;
import com.nongviet201.cinema.core.service.EmployeeService;
import com.nongviet201.cinema.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nongviet201.cinema.core.utils.DateTimeUtils.parseDate;

@Service
@AllArgsConstructor
public class AdminEmployeesService {

    private final EmployeeService employeeService;
    private final AdminEmployeeDecorator decorator;
    private final UserService userService;

    public List<AdminEmployeeResponse> getEmployeesDataFilter(
        UpsertEmployeeRequest.EmployeeFilter request
    ) {
        if (request.getToDate().isEmpty()) {
            return employeeService.getEmployeeFilter(
                    parseDate(request.getFormDate()),
                    parseDate(request.getFormDate())
                ).stream()
                .map(decorator::decorate)
                .toList();
        }
        return employeeService.getEmployeeFilter(
                parseDate(request.getFormDate()),
                parseDate(request.getToDate())
            ).stream()
            .map(decorator::decorate)
            .toList();
    }

    public AdminEmployeeResponse getEmployeeById(int id) {
        return decorator.decorate(
            employeeService.getEmployeeById(id)
        );
    }

    public AdminEmployeeResponse getEmployeeFindByData(
        UpsertEmployeeRequest.Find request
    ) {
        if (!request.getPhoneNumber().isEmpty()) {
            return decorator.decorate(
                employeeService.getEmployeeByUserId(
                    userService.getUserByPhoneNumber(request.getPhoneNumber()).getId()
                )
            );
        }

        return getEmployeeById(request.getId());
    }
}
