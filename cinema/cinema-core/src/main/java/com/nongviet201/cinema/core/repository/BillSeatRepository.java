package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.bill.BillSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillSeatRepository extends JpaRepository<BillSeat, Integer> {
    List<BillSeat> findByBillId(Integer billId);
}
