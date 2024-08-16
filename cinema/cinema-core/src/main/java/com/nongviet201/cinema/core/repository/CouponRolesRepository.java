package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.bill.coupon.CouponRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRolesRepository extends JpaRepository<CouponRoles, Integer> {
}
