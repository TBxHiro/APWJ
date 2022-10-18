<%--
  Created by IntelliJ IDEA.
  User: Safin-Lenovo
  Date: 18-Oct-22
  Time: 10:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Calculator</title>
</head>
<body>

<form method="post" action="cal/add">
    <label for="num1">Number 1:</label>
    <input type="number" name="num1" id="num1"/>

    <br><br>

    <label for="num2">Number 2:</label>
    <input type="number" name="num2" id="num2"/>

    <br><br>

    <input type="submit" value="Calculate">
</form>
<br>
<h4>Result: <% out.println(request.getAttribute("result")); %>
</h4>
</body>
</html>
