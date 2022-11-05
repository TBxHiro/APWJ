<%--
  Created by IntelliJ IDEA.
  User: Safin
  Date: 11/5/2022
  Time: 12:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<form:form action="calculate" modelAttribute="taxableIncome">
    Choose Tax Payer Category: <form:select path="category_select">
    <form:option value="General" label="General"/>
    <form:option value="Female/Senior Citizen" label="Female/Senior Citizen"/>
    <form:option value="Disabled" label="Disabled"/>
    <form:option value="Gazetted Freedom Fighters" label="Gazetted Freedom Fighters"/>
</form:select>
    <br>
    <label for="name">Name</label>
    <form:input path="name" id="name"/>
    <form:errors path="name"/>
    <br>
    <label for="basic_salary">Basic Salary</label>
    <form:input path="basic_salary" id="basic_salary"/>
    <form:errors path="basic_salary"/>
    <br>
    <label for="houserent">House Rent</label>
    <form:input path="houserent" id="houserent"/>
    <form:errors path="houserent"/>
    <br>
    <label for="medical">Medical Allowance</label>
    <form:input path="medical" id="medical"/>
    <form:errors path="medical"/>
    <br>
    <label for="conveyance">Conveyance</label>
    <form:input path="conveyance" id="conveyance"/>
    <form:errors path="conveyance"/>
    <br>
    <label for="commision">Incentive/OT</label>
    <form:input path="commission" id="commision"/>
    <form:errors path="commission"/>
    <br>
    <label for="bonus">Festival Bonus</label>
    <form:input path="bonus" id="bonus"/>
    <form:errors path="bonus"/>
    <br>

    <br><br>

    <input type="submit" value="Calculate">

</form:form>
</body>
</html>
