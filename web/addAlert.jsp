<%-- 
    Document   : addAlert
    Created on : Jun 5, 2025, 1:48:55 PM
    Author     : loan1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Alert Page</title>
</head>
<body>
    <h1>Add Alert</h1>

    <form action="MainController" method="POST">
        Ticker: <input type="text" name="ticker" placeholder="Enter ticker" required /><br/>
        Threshold: <input type="number" name="threshold" placeholder="Enter threshold" step="0.01" required /><br/>
        Direction: 
        <select name="direction" required>
            <option value="increase">increase</option>
            <option value="decrease">decrease</option>
        </select><br/>
        <input type="submit" name="action" value="CreateAlert" /><br/>
        <a href="alertList.jsp">Back to Alert List</a>
    </form>

    <% String msg = (String) request.getAttribute("MSG"); %>
    <% if (msg != null) { %>
        <p style="color: red;">Message: <%= msg %></p>
    <% } %>
</body>
</html>
