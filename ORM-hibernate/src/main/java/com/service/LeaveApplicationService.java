package com.service;

import com.constant.EmployeeStatus;
import com.constant.Gender;
import com.domain.Employee;
import com.domain.LeaveApplication;
import com.repository.LeaveApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;

@Service
@Transactional
public class LeaveApplicationService {

    private LeaveApplicationRepository leaveApplicationRepository;

    public LeaveApplicationService(LeaveApplicationRepository leaveApplicationRepository) {
        this.leaveApplicationRepository = leaveApplicationRepository;
    }

    @Transactional
    public boolean insert(LeaveApplication leaveApplication) throws SQLException {
        leaveApplication.setReason("Tesating Reason");
        leaveApplication.setTotalDays(5);
        leaveApplication.setFromDate(LocalDate.of(2022,11,20));
        leaveApplication.setToDate(LocalDate.of(2022,11,24));


        return leaveApplicationRepository.create(leaveApplication);
    }
}
