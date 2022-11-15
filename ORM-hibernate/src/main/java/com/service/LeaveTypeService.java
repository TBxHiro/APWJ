package com.service;

import com.constant.EmployeeStatus;
import com.constant.Gender;
import com.domain.Employee;
import com.domain.LeaveType;
import com.repository.LeaveTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;

@Service
@Transactional
public class LeaveTypeService {

    private LeaveTypeRepository leaveTypeRepository;

    public LeaveTypeService(LeaveTypeRepository leaveTypeRepository) {
        this.leaveTypeRepository = leaveTypeRepository;
    }

    @Transactional
    public boolean insert(LeaveType leaveType) throws SQLException {
        leaveType.setCategory("Annual Leave");
        leaveType.setTotalDays(10);
//        leaveType.setCategory("Sick Leave");
//        leaveType.setTotalDays(7);
        return leaveTypeRepository.create(leaveType);
    }
}
