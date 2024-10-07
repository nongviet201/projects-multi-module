package com.nongviet201.cinema.core.service;

import com.nongviet201.cinema.core.entity.user.Employee;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface EmployeeService {

    Employee getEmployeeById(int id);

    List<Employee> getAllEmployees();

    Employee saveEmployee(Employee employee);

    Employee getEmployeeByUserId(int id);

    List<Employee> getEmployeeFilter(LocalDate formDate, LocalDate toDate);
}
