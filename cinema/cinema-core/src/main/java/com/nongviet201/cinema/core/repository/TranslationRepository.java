package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.bill.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Integer> {

    Optional<Translation> findByBillId(Integer billId);
}
