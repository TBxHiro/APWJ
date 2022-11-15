package com.controller;

import com.constant.EmployeeStatus;
import com.constant.Gender;
import com.domain.Employee;
import com.domain.LeaveType;
import com.service.EmployeeService;
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
import java.time.LocalDate;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/show")
    public String show(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "registration";
    }

    @RequestMapping("/create")
    public String create(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult) throws SQLException {
        if (!bindingResult.hasErrors()) {
            System.out.println("Error in controller");
            return "registration";
        }

        employeeService.insert(new Employee());
        return "registration";
    }


    @RequestMapping("/submit")
    public String submit(@Valid @ModelAttribute("student") Employee employee,
                         BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            employeeService.insert(employee);
            return "confirmation";
        }
    }
}
