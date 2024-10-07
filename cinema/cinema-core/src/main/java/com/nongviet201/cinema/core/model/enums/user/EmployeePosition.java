package com.nongviet201.cinema.core.model.enums.user;

import lombok.Getter;

@Getter
public enum EmployeePosition {
    TICKET_BOX("Nhân viên bán vé"), // QUẦY BÁN VÉ
    CONCESSION("Nhân viên quầy bỏng nước"), // QUẦY BỎNG NƯỚC
    PROJECTOR("Nhân viên phòng máy"), // PHÒNG MÁY
    TICKET_CHECKER("Nhân viên soát vé"), // QUẦY SOÁT VÉ
    MANAGER("Quản lý");

    private final String message;

    EmployeePosition(String message) {
        this.message = message;
    }
}
