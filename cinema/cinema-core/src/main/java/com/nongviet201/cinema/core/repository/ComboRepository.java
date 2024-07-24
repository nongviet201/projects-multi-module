package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.model.entity.bill.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Integer> {
}
