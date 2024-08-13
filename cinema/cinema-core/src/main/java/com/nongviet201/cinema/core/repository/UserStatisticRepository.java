package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.user.UserStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserStatisticRepository extends JpaRepository<UserStatistic, Integer> {
    Optional<UserStatistic> findByUserId(Integer userId);
}
