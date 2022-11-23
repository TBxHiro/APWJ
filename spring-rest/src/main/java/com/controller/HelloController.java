package com.controller;

import com.domain.Currency;
import com.domain.LeaveApplication;
import com.domain.LeaveType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.LeaveApplicationService;
import com.service.LeaveTypeService;
import com.service.StudentService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private LeaveApplicationService leaveApplicationService;

    private LeaveTypeService leaveTypeService;

    public HelloController(LeaveApplicationService leaveApplicationService,
                           LeaveTypeService leaveTypeService) {
        this.leaveApplicationService = leaveApplicationService;
        this.leaveTypeService = leaveTypeService;
    }

    @RequestMapping("/sayHello")
    public String greet() {
        return "Hello Java Developers";
    }

    @RequestMapping("/get")
    public LeaveApplication get() {
        LeaveApplication leaveApplication = leaveApplicationService.get(2L);
        System.out.println(leaveApplication.getId() + " " + leaveApplication.getLeaveType().getCategory() + " " + leaveApplication.getTotalDays() + " " + leaveApplication.getReason());
        return leaveApplication;
    }

    @RequestMapping("/get/v2")
    public LeaveType fetch() {
        LeaveType leaveType = leaveTypeService.get(1L);
        System.out.println(leaveType.getId() + " " + leaveType.getCategory() + " " + leaveType.getTotalDays());
        return leaveType;
    }

    @RequestMapping("/test")
    public String test() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        LeaveType leaveType =
                objectMapper.readValue(new ClassPathResource("leave_type.json").getFile(), LeaveType.class);
        System.out.println(leaveType.getCategory() + " " + leaveType.getTotalDays());
        return leaveType.getCategory() + " " + leaveType.getTotalDays();
    }

    @CrossOrigin
    @GetMapping("/currency/rate")
    public Currency rate() throws IOException {
        Currency currency = new Currency();
        currency.setRate(101.2);
        return currency;
    }
    @CrossOrigin
    @GetMapping("/currency/from/{from}/to/{to}")
    public Currency currencyRate(@PathVariable String from, @PathVariable String to) throws IOException {
        Currency currency = new Currency();

        if(from.equals("usd") && to.equals("bdt")){
            currency.setRate(102.2);
        } else if(from.equals("bdt") && to.equals("usd")){
            currency.setRate(0.01);
        } else{
            currency.setRate(0);
        }
        return currency;
    }
}
