package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.bill.Transaction;
import com.nongviet201.cinema.core.payment.vnpay.code.ResponseCodeVNPAY;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Optional<Transaction> findByBillId(Integer billId);

    List<Transaction> findAllByCinema_IdAndPayDateBetweenAndStatusAndResponseCodeVNPAY(
        int cinemaId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Boolean status,
        ResponseCodeVNPAY responseCodeVNPAY
    );

    List<Transaction> findAllByPayDateBetweenAndStatusAndResponseCodeVNPAY(
        LocalDateTime startDate,
        LocalDateTime endDate,
        Boolean status,
        ResponseCodeVNPAY responseCodeVNPAY
    );

    List<Transaction> findAllByPayDateBetweenAndCinema_Id(
        LocalDateTime startDate,
        LocalDateTime endDate,
        int cinemaId
    );

    List<Transaction> findAllByPayDateBetween(
        LocalDateTime startDate,
        LocalDateTime endDate
    );
}
