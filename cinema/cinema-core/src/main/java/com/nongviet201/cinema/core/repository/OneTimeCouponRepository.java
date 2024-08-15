package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.bill.coupon.OneTimeCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OneTimeCouponRepository extends JpaRepository<OneTimeCoupon, Integer> {
}
