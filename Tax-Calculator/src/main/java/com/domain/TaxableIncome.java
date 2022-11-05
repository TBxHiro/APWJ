package com.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TaxableIncome {
    //@NotNull(message = "Select a category")
    private String category_select;
    private Long id;
    //@Size(min = 5, max = 20, message = "The Name '${validatedValue}' must be between {min} and {max} characters long")
    private String name;
    @NotNull(message = "Input the salary")
    private double basic_salary;
    private double basic_salary_exemption;
    private double basic_salary_taxable;

    //@NotNull(message = "Input the houserent")
    private double houserent;
    private double houserent_exemption;
    private double houserent_taxable;
    //@NotNull(message = "Input medical allowance")
    private double medical;
    private double medical_exemption;
    private double medical_taxable;
    private double conveyance;
    private double conveyance_exemption;
    private double conveyance_taxable;
    private double commission;
    private double commission_exemption;
    private double commission_taxable;
    private double bonus;
    private double bonus_exemption;
    private double bonus_taxable;
    private double totalIncome;
    private double totalTaxable;
    private double grossTax;

    public String getCategory_select() {
        return category_select;
    }

    public void setCategory_select(String category_select) {
        this.category_select = category_select;
    }

    public double getBasic_salary() {
        return basic_salary;
    }

    public void setBasic_salary(double basic_salary) {
        this.basic_salary = basic_salary;
    }

    public double getHouserent() {
        return houserent;
    }

    public void setHouserent(double houserent) {
        this.houserent = houserent;
    }

    public double getMedical() {
        return medical;
    }

    public void setMedical(double medical) {
        this.medical = medical;
    }

    public double getConveyance() {
        return conveyance;
    }

    public void setConveyance(double conveyance) {
        this.conveyance = conveyance;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getBasic_salary_exemption() {
        return basic_salary_exemption;
    }

    public void setBasic_salary_exemption(double basic_salary_exemption) {
        this.basic_salary_exemption = basic_salary_exemption;
    }

    public double getBasic_salary_taxable() {
        return basic_salary_taxable;
    }

    public void setBasic_salary_taxable(double basic_salary_taxable) {
        this.basic_salary_taxable = basic_salary_taxable;
    }

    public double getHouserent_exemption() {
        return houserent_exemption;
    }

    public void setHouserent_exemption(double houserent_exemption) {
        this.houserent_exemption = houserent_exemption;
    }

    public double getHouserent_taxable() {
        return houserent_taxable;
    }

    public void setHouserent_taxable(double houserent_taxable) {
        this.houserent_taxable = houserent_taxable;
    }

    public double getMedical_exemption() {
        return medical_exemption;
    }

    public void setMedical_exemption(double medical_exemption) {
        this.medical_exemption = medical_exemption;
    }

    public double getMedical_taxable() {
        return medical_taxable;
    }

    public void setMedical_taxable(double medical_taxable) {
        this.medical_taxable = medical_taxable;
    }

    public double getConveyance_exemption() {
        return conveyance_exemption;
    }

    public void setConveyance_exemption(double conveyance_exemption) {
        this.conveyance_exemption = conveyance_exemption;
    }

    public double getConveyance_taxable() {
        return conveyance_taxable;
    }

    public void setConveyance_taxable(double conveyance_taxable) {
        this.conveyance_taxable = conveyance_taxable;
    }

    public double getCommission_exemption() {
        return commission_exemption;
    }

    public void setCommission_exemption(double commission_exemption) {
        this.commission_exemption = commission_exemption;
    }

    public double getCommission_taxable() {
        return commission_taxable;
    }

    public void setCommission_taxable(double commission_taxable) {
        this.commission_taxable = commission_taxable;
    }

    public double getBonus_exemption() {
        return bonus_exemption;
    }

    public void setBonus_exemption(double bonus_exemption) {
        this.bonus_exemption = bonus_exemption;
    }

    public double getBonus_taxable() {
        return bonus_taxable;
    }

    public void setBonus_taxable(double bonus_taxable) {
        this.bonus_taxable = bonus_taxable;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalTaxable() {
        return totalTaxable;
    }

    public void setTotalTaxable(double totalTaxable) {
        this.totalTaxable = totalTaxable;
    }

    public double getGrossTax() {
        return grossTax;
    }

    public void setGrossTax(double grossTax) {
        this.grossTax = grossTax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
