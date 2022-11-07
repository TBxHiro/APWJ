<%--
  Created by IntelliJ IDEA.
  User: Safin
  Date: 11/5/2022
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
    <%@include file="/WEB-INF/css/style.css" %>
</style>

<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Name: ${taxable.name}
    <br>
    Tax Payer Category: ${taxable.category_select}
</p>
<p a>Taxable Income Calculation:</p>
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
    <tr>
        <th>Gross Tax Liability</th>
        <td>-</td>
        <td>-</td>
        <th id="gross_tax">${taxable.grossTax}</th>
    </tr>
</table>

<br>
<table>
    <tr>
        <th>ID</th>
        <th>Tax Category</th>
        <th>Salary</th>
        <th>House Rent</th>
        <th>Medical</th>
        <th>Conveyance</th>
        <th>OT</th>
        <th>Commission</th>
        <th>Bonus</th>
        <th>Taxable Amount</th>
        <th>Gross Tax</th>
    </tr>
    <c:forEach items="${taxHistory}" var="tax">
        <tr>
                <%--        <c:url var="updateLink" value="/tax/edit">--%>
                <%--            <c:param name="id" value="${tax.id}" />--%>
                <%--        </c:url>--%>
                <%--        <c:url var="deleteLink" value="/tax/delete">--%>
                <%--            <c:param name="id" value="${tax.id}" />--%>
                <%--        </c:url>--%>
            <td>${tax.id}</td>
            <td>${tax.category_select}</td>
            <td>${tax.name}</td>
            <td>${tax.basic_salary}</td>
            <td>${tax.houserent}</td>
            <td>${tax.medical}</td>
            <td>${tax.conveyance}</td>
            <td>${tax.commission}</td>
            <td>${tax.bonus}</td>
            <td>${tax.totalTaxable}</td>
            <td>${tax.grossTax}</td>

                <%--        <td><a href="${updateLink}">Update</a> | <a href="${deleteLink}">Delete</a></td>--%>
        </tr>
    </c:forEach>
</table>
</body>
</html>
