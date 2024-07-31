package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.model.entity.bill.BillCombo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillComboRepository extends JpaRepository<BillCombo, Integer> {
}
