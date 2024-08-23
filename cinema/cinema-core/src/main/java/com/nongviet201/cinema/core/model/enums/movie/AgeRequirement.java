package com.nongviet201.cinema.core.model.enums.movie;

import lombok.Getter;

@Getter
public enum AgeRequirement {
    P("Phim được phép phổ biến đến người xem ở mọi độ tuổi."),
    K("Phim được phổ biến đến người xem dưới 13 tuổi và có người bảo hộ đi kèm."),
    T13("Phim được phổ biến đến người xem từ đủ 13 tuổi trở lên (13+)."),
    T16("Phim được phổ biến đến người xem từ đủ 16 tuổi trở lên (16+)."),
    T18("Phim được phổ biến đến người xem từ đủ 18 tuổi trở lên (18+)."),
    C("Phim không được phép phổ biến.");

    private final String description;

    AgeRequirement(String description) {
        this.description = description;
    }
}
