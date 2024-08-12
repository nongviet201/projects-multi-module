package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.user.TokenConfirm;
import com.nongviet201.cinema.core.model.enums.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenConfirmRepository extends JpaRepository<TokenConfirm, Integer> {
    Optional<TokenConfirm> findByTokenAndType(String token, TokenType tokenType);
}
