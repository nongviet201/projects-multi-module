package com.nongviet201.cinema.core.entity.bill.coupon;

import com.nongviet201.cinema.core.model.enums.coupon.DiscountCouponType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@MappedSuperclass
public abstract class BaseCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String code;
    private Integer limitAmount;
    private Integer usedAmount;

    @Enumerated(EnumType.STRING)
    private DiscountCouponType discountCouponType;
    private Integer discount;

    // Ngày bắt đầu và kết thúc
    private LocalDate startDate;
    private LocalDate endDate;

    // số lượng vé hoặc combo tối thiểu hoặc tối đa dành cho coupon
    private Integer minQuantity;
    private Integer maxQuantity;

    // giá tối thiểu hoặc tối đa dành cho coupon
    private Long minPrice;
    private Long maxPrice;

    private Integer ageRequired; // nếu thêm trường này chỉ những tài khoản đủ tuổi mới có thể sử dụng

    //Khung giờ áp dụng
    private LocalTime enableTime;
    private LocalTime endEnableTime;

    // Đặt trước ? (nếu điền trường này, vé chỉ có hiệu lực từ trước thời gian này)
    private LocalDate reservationDate;

    // Trạng thái mã
    private boolean status;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    public boolean isExpired() {
        return LocalDate.now().isAfter(this.getEndDate());
    }

    public void createCoupon() {
        this.status = true;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public void updateCoupon() {
        this.updatedAt = LocalDate.now();
    }

}
