package com.nongviet201.cinema.admin.sdk.decorator;

import com.nongviet201.cinema.admin.sdk.converter.AdminEmployeeToResponseConverter;
import com.nongviet201.cinema.admin.sdk.response.AdminEmployeeResponse;
import com.nongviet201.cinema.core.entity.user.Employee;
import com.nongviet201.cinema.core.utils.WebFormatter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminEmployeeDecorator {

    private final AdminEmployeeToResponseConverter converter;
    private final WebFormatter webFormatter;

    public AdminEmployeeResponse decorate (
        Employee employee
    ) {
        return converter.convert(
            employee.getId(),
            employee.getUser().getFullName(),
            employee.getUser().getEmail(),
            employee.getUser().getPhoneNumber(),
            employee.getUser().getGender(),
            webFormatter.formatDateToDDmmYYYY(employee.getUser().getBirthday()),
            employee.getPosition().getMessage(),
            employee.getStatus().getMessage(),
            webFormatter.formatDateToDDmmYYYY(employee.getJoinDate())
        );
    }
}
