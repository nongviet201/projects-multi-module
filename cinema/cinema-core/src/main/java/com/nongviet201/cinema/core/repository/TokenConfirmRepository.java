package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.user.TokenConfirm;
import com.nongviet201.cinema.core.model.enums.user.TokenType;
import jakarta.validation.constraints.Null;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenConfirmRepository extends JpaRepository<TokenConfirm, Integer> {
    Optional<TokenConfirm> findByTokenAndType(String token, TokenType tokenType);

    List<TokenConfirm> findByConfirmedAt(LocalDateTime dateTime);
}
