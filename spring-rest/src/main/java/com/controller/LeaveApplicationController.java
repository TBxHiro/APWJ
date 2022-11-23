package com.controller;

import com.domain.LeaveApplication;
import com.domain.LeaveType;
import com.domain.Student;
import com.service.LeaveApplicationService;
import com.service.LeaveTypeService;
import com.service.StudentService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

@Controller
@RequestMapping("/leaveApplications")
public class LeaveApplicationController {

    private LeaveApplicationService leaveApplicationService;

    private LeaveTypeService leaveTypeService;

    private StudentService studentService;

    public LeaveApplicationController(LeaveApplicationService leaveApplicationService, LeaveTypeService leaveTypeService, StudentService studentService) {
        this.leaveApplicationService = leaveApplicationService;
        this.leaveTypeService = leaveTypeService;
        this.studentService = studentService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/create")
    public void create() throws Exception {
        LeaveType leaveType = leaveTypeService.get(1L);
        Student student = studentService.get(4L);

        LeaveApplication leaveApplication = new LeaveApplication();
        leaveApplication.setStudent(student);
        leaveApplication.setLeaveType(leaveType);

        leaveApplication.setFromDate(LocalDate.of(2022, 10, 20));
        leaveApplication.setToDate(LocalDate.of(2022, 10, 28));
        leaveApplication.setTotalDays(9);
        leaveApplication.setReason("High Fever");
        boolean result = leaveApplicationService.insert(leaveApplication);
        if (!result) {
            throw new Exception("Constraint Violation");
        }
    }

    @RequestMapping("/get")
    public void get() {
        LeaveApplication leaveApplication = leaveApplicationService.get(1L);
        System.out.println(leaveApplication.getId() + " " + leaveApplication.getLeaveType().getCategory() + " " + leaveApplication.getTotalDays() + " " + leaveApplication.getReason());
    }
}
