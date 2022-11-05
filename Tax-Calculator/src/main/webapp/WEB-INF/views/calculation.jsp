<%--
  Created by IntelliJ IDEA.
  User: Safin
  Date: 11/5/2022
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    <%@include file="/WEB-INF/css/style.css" %>
</style>

<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Tax Payer Category: ${taxable.category_select}</p>
<br><br>
<table>
    <tr>
        <th>Area</th>
        <th>Amount</th>
        <th>Max. Exemption</th>
        <th>Taxable</th>
    </tr>
    <tr>
        <td>Basic Salary</td>
        <td id="basic_salary_show">${taxable.basic_salary}</td>
        <td id="basic_salary_exemption">${taxable.basic_salary_exemption}</td>
        <td id="basic_salary_taxable">${taxable.basic_salary_taxable}</td>
    </tr>
    <tr>
        <td>House Rent</td>
        <td id="houserent_show">${taxable.houserent}</td>
        <td id="houserent_exemption">${taxable.houserent_exemption}</td>
        <td id="houserent_taxable">${taxable.houserent_taxable}</td>
    </tr>
    <tr>
        <td>Medical Allowance</td>
        <td id="medical_show">${taxable.medical}</td>
        <td id="medical_exemption">${taxable.medical_exemption}</td>
        <td id="medical_taxable">${taxable.medical_taxable}</td>
    </tr>
    <tr>
        <td>Conveyance</td>
        <td id="conveyance_show">${taxable.conveyance}</td>
        <td id="conveyance_exemption">${taxable.conveyance_exemption}</td>
        <td id="conveyance_taxable">${taxable.conveyance_taxable}</td>
    </tr>
    <tr>
        <td>Incentive/OT</td>
        <td id="commision_show">${taxable.commission}</td>
        <td id="commision_exemption">${taxable.commission_exemption}</td>
        <td id="commision_taxable">${taxable.commission_taxable}</td>
    </tr>
    <tr>
        <td>Festival Bonus</td>
        <td id="bonus_show">${taxable.bonus}</td>
        <td id="bonus_exemption">${taxable.bonus_exemption}</td>
        <td id="bonus_taxable">${taxable.bonus_taxable}</td>
    </tr>
    <tr>
        <th>Total</th>
        <td id="total_income">${taxable.totalIncome}</td>
        <td>-</td>
        <th id="total_taxable">${taxable.totalTaxable}</th>
    </tr>
</table>
</body>
</html>
