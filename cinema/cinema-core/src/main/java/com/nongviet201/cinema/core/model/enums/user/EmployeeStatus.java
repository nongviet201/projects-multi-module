package com.nongviet201.cinema.core.model.enums.user;

import lombok.Getter;

@Getter
public enum EmployeeStatus {
    PROBATION("Nhân viên thử việc"),
    OFFICIAL_EMPLOYEE("Nhân viên chính thức"),
    RESIGNED("Đã nghỉ việc");

    private final String message;

    EmployeeStatus(String message) {
        this.message = message;
    }
}
