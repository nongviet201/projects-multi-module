package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.bill.coupon.BaseCouponRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseCouponRuleRepository extends JpaRepository<BaseCouponRule, Integer> {
}
