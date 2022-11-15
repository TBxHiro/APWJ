package com.controller;

import com.domain.Employee;
import com.domain.LeaveApplication;
import com.domain.LeaveType;
import com.service.EmployeeService;
import com.service.LeaveApplicationService;
import com.service.LeaveTypeService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
@RequestMapping("/leaveTypes")
public class LeaveApplicationController {
    private LeaveApplicationService leaveApplicationService;

    public LeaveApplicationController(LeaveApplicationService leaveApplicationService) {
        this.leaveApplicationService = leaveApplicationService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/create")
    public String create(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult) throws SQLException {
        if (!bindingResult.hasErrors()) {
            System.out.println("Error in controller");
            return "registration";
        }

        leaveApplicationService.insert(new LeaveApplication());
        return "registration";
    }
}
