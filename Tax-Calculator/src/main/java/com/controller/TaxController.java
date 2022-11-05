package com.controller;

import com.domain.TaxableIncome;
import com.repository.TaxableRepo;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import javax.validation.Valid;
import java.sql.SQLException;

@Controller
@RequestMapping("/tax")
public class TaxController {
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    private DataSource dataSource;
    public TaxController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @RequestMapping("/calculator")
    public String createForm(Model model) {
        model.addAttribute("taxableIncome", new TaxableIncome());
        return "calculator";
    }

    @RequestMapping("/calculate")
    public String calculateTax(@Valid @ModelAttribute("taxableIncome") TaxableIncome taxableIncome, Model model, BindingResult bindingResult, Errors errors) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "calculator";
        }
        //TaxableIncome taxable = TaxableRepo.taxableIncomeCalculation(taxableIncome);
        double basic_salary = taxableIncome.getBasic_salary();
        double exemption_basic_salary = 0;
        double taxable_basic_salary = Math.round(basic_salary);
        taxableIncome.setBasic_salary_exemption(exemption_basic_salary);
        taxableIncome.setBasic_salary_taxable(taxable_basic_salary);

        double exemption_houseRent = Math.min(basic_salary * 0.5, 12 * 25000);
        double taxable_houseRent = taxableIncome.getHouserent() - exemption_houseRent < 0 ? 0 : taxableIncome.getHouserent() - exemption_houseRent;
        taxableIncome.setHouserent_exemption(exemption_houseRent);
        taxableIncome.setHouserent_taxable(taxable_houseRent);

        double exemption_medical = Math.min(basic_salary * 0.1, 120000);
        double taxable_medical = taxableIncome.getMedical() - exemption_medical < 0 ? 0 : taxableIncome.getMedical() - exemption_medical;
        taxableIncome.setMedical_exemption(exemption_medical);
        taxableIncome.setMedical_taxable(taxable_medical);

        double exemption_conveyance = 30000;
        double taxable_conveyance = taxableIncome.getConveyance() - exemption_conveyance < 0 ? 0 : taxableIncome.getConveyance() - exemption_conveyance;
        taxableIncome.setConveyance_exemption(exemption_conveyance);
        taxableIncome.setConveyance_taxable(taxable_conveyance);

        double taxable_commission = taxableIncome.getCommission();
        taxableIncome.setCommission_exemption(0);
        taxableIncome.setCommission_taxable(taxable_commission);

        double taxable_bonus = taxableIncome.getBonus();
        taxableIncome.setBonus_exemption(0);
        taxableIncome.setBonus_taxable(taxable_bonus);

        double totalIncome = basic_salary + taxableIncome.getMedical() + taxableIncome.getMedical()
                + taxableIncome.getConveyance() + taxableIncome.getCommission() + taxableIncome.getBonus();
        double totalTaxable = taxable_basic_salary + taxable_houseRent + taxable_medical + taxable_conveyance
                + taxable_commission + taxable_bonus;
        taxableIncome.setTotalIncome(totalIncome);
        taxableIncome.setTotalTaxable(totalTaxable);

        TaxableRepo taxableRepo = new TaxableRepo(dataSource);
        taxableRepo.create(taxableIncome);
        model.addAttribute("taxable", taxableIncome);
        return "calculation";
    }
}