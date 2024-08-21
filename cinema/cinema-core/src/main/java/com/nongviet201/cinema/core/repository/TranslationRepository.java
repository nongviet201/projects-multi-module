package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.bill.Translation;
import com.nongviet201.cinema.core.model.enums.bill.BillStatus;
import com.nongviet201.cinema.core.payment.vnpay.code.ResponseCodeVNPAY;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Integer> {

    Optional<Translation> findByBillId(Integer billId);

    List<Translation> findAllByCinema_IdAndPayDateBetweenAndStatusAndResponseCodeVNPAY(
        int cinemaId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Boolean status,
        ResponseCodeVNPAY responseCodeVNPAY
    );
}
