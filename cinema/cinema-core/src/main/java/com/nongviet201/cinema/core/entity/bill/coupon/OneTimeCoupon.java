package com.nongviet201.cinema.core.entity.bill.coupon;

import com.nongviet201.cinema.core.entity.user.User;
import com.nongviet201.cinema.core.model.enums.coupon.DiscountCouponType;
import com.nongviet201.cinema.core.model.enums.coupon.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "one_time_coupon")
public class OneTimeCoupon extends BaseCoupon{

    @Enumerated(EnumType.STRING)
    private UserType userType; // dựa trên kiểu người dùng

    // [CLONE]
    private LocalDate startDate;
    private LocalDate endDate; // dựa trên số ngày hiệu lực
    private Integer effectiveDate; // số ngày hiệu lực của mã giảm giá

    // [BASE]
    private LocalDate startEnableDate; // ngày mã có thể sử dụng
    private LocalDate endEnableDate; // ngày mã hết thời gian sử dụng //nếu để null thì mã sẽ hiệu lực vô hạn

    private boolean isUsed; // mã đã dùng hay chưa
    private boolean isBase;

    // [CLONE]
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user; // nếu mã này không phải base thì nó thuộc về 1 người dùng duy nhất, nếu là base thì null
    private Integer baseCouponId; // id này trỏ tới base nếu có 1 thay đổi quan trọng trên base thì tất cả clone phải cập nhật ngay lập tức

}
