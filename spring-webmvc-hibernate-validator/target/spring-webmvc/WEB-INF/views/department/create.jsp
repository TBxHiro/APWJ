<%--
  Created by IntelliJ IDEA.
  User: kawsu
  Date: 10/25/2022
  Time: 9:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create Department</title>
</head>
<body>

<h1>Create Department</h1>

<form:form action="create" modelAttribute="department">
    <label for="department_name">Name:</label>
    <form:input path="name" id="department_name"/>
    <form:errors path="name"/>

    <br><br>

    <input type="submit">

</form:form>

</body>
</html>
