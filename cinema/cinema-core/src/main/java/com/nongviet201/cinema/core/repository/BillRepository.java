package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.bill.Bill;
import com.nongviet201.cinema.core.model.enums.bill.BillStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    List<Bill> findByUserId(Integer userId);

    List<Bill> findByUserIdAndStatusOrderByPaymentAtDesc(Integer userId, BillStatus status);
}
