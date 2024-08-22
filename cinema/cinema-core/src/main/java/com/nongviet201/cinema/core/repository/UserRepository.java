package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.user.User;
import com.nongviet201.cinema.core.model.enums.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);

    List<User> findByRoleAndEnabledAndCreatedAtBetween(UserRole role, boolean enabled, LocalDate startDate, LocalDate endDate);
}
