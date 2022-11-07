package com.controller;

import com.domain.TaxableIncome;
import com.repository.TaxableRepository;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
@RequestMapping("/tax")
public class TaxController {
    private TaxableRepository taxableRepository;

    public TaxController(TaxableRepository taxableRepository) {
        this.taxableRepository = taxableRepository;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/calculator")
    public String createForm(Model model) {
        TaxableIncome taxableIncome = new TaxableIncome();
        model.addAttribute("taxableIncome", taxableIncome);
        return "calculator";
    }

    @RequestMapping("/calculate")
    public String form(@Valid @ModelAttribute("taxableIncome") TaxableIncome taxableIncome, BindingResult bindingResult, Model model, Errors errors) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "calculator";
        } else if (errors.hasErrors()) {
            return "calculator";
        }
        //TaxableIncome taxable = TaxableRepo.taxableIncomeCalculation(taxableIncome);
        double basic_salary = taxableIncome.getBasic_salary();
        double exemption_basic_salary = 0;
        double taxable_basic_salary = (Long) Math.round(basic_salary);
        taxableIncome.setBasic_salary_exemption(exemption_basic_salary);
        taxableIncome.setBasic_salary_taxable(taxable_basic_salary);

        double exemption_houseRent = Math.min(basic_salary * 0.5, 12 * 25000);
        double taxable_houseRent = taxableIncome.getHouserent() - exemption_houseRent < 0 ? 0 : taxableIncome.getHouserent() - exemption_houseRent;
        taxableIncome.setHouserent_exemption(exemption_houseRent);
        taxableIncome.setHouserent_taxable(taxable_houseRent);

        double exemption_medical = Math.round(Math.min(basic_salary * 0.1, 120000));
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

        String category = taxableIncome.getCategory_select();
        Double taxBase = (category == "General" ? 300000.0 : category == "Female/Senior Citizen" ? 350000.0 :
                category == "Disabled" ? 450000.0 : 475000.0);
        Double grossTax = 0.0;
        if (totalTaxable > taxBase) {
            totalTaxable -= taxBase;
            grossTax += Math.round((totalTaxable > 100000 ? 100000 : totalTaxable) * 0.5);
            totalTaxable -= 100000;
            grossTax += Math.round((totalTaxable > 300000 ? 300000 : totalTaxable > 0 ? totalTaxable : 0) * 0.1);
            totalTaxable -= 300000;
            grossTax += Math.round((totalTaxable > 400000 ? 400000 : totalTaxable > 0 ? totalTaxable : 0) * 0.15);
            totalTaxable -= 400000;
            grossTax += Math.round((totalTaxable > 500000 ? 500000 : totalTaxable > 0 ? totalTaxable : 0) * 0.2);
            totalTaxable -= 500000;
            grossTax += Math.round((totalTaxable > 0 ? totalTaxable : 0) * 0.25);
        }
//        Long taxSlab1 = Math.round(totalTaxable - taxBase > 100000 ? 100000 : totalTaxable - taxBase);
//        Long taxSlab2 = Math.round(totalTaxable - taxBase - 100000 > 300000 ? 300000 : totalTaxable - taxBase - 100000);
//        Long taxSlab3 = Math.round(totalTaxable - taxBase - 400000 > 400000 ? 400000 : totalTaxable - taxBase - 400000);
//        Long taxSlab4 = Math.round(totalTaxable - taxBase - 800000 > 500000 ? 500000 : totalTaxable - taxBase - 800000);
//        Long taxSlab5 = Math.round(totalTaxable - taxBase - 1300000 > 0 ? totalTaxable - taxBase - 1300000 : 0);

        //Double grossTax = taxBase * 0 + taxSlab1 * Math.round(totalTaxable - taxBase > 100000 ? 100000 : totalTaxable - taxBase); + taxSlab2 * 0.1 + taxSlab3 * 0.15 + taxSlab4 * 0.2 + taxSlab5 * 0.25;
        taxableIncome.setGrossTax(grossTax);

        taxableRepository.create(taxableIncome);
        model.addAttribute("taxable", taxableIncome);
        model.addAttribute("taxHistory", taxableRepository.list());

        return "calculation";
    }
}