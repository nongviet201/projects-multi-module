package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.entity.user.Employee;
import com.nongviet201.cinema.core.exception.BadRequestException;
import com.nongviet201.cinema.core.repository.EmployeeRepository;
import com.nongviet201.cinema.core.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeById(
        int id
    ) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("Không tìm thấy thông tin nhân viên với mã: " + id));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(
        Employee employee
    ) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeByUserId(int id) {
        return employeeRepository.findByUserId(id)
            .orElseThrow(() -> new BadRequestException("Không tìm thấy thông tin nhân viên dựa trên id người dùng: " + id));
    }

    @Override
    public List<Employee> getEmployeeFilter(
        LocalDate formDate,
        LocalDate toDate
    ) {
        return employeeRepository.findByJoinDateBetweenOrderByJoinDate(
            formDate,
            toDate
        );
    }
}
