package com.service;

import com.constant.EmployeeStatus;
import com.constant.Gender;
import com.domain.Employee;
import com.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;

@Service
@Transactional
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public boolean insert(Employee employee) throws SQLException {
        employee.setEmail("safin@gmail.com");
        employee.setGender(Gender.MALE);
        employee.setFirstName("Safin3");
        employee.setLastName("Zaman");
        employee.setJoinDate(LocalDate.now());
        employee.setStatus(EmployeeStatus.ACTIVE);
        return employeeRepository.create(employee);
    }
}
