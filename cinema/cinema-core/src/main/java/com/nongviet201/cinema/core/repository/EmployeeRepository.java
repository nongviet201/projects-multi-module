package com.nongviet201.cinema.core.repository;

import com.nongviet201.cinema.core.entity.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByJoinDateBetweenOrderByJoinDate(LocalDate formDate, LocalDate toDate);

    Optional<Employee> findByUserId(int id);
}
