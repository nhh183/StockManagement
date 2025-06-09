<%-- 
    Document   : updateAlert
    Created on : Jun 8, 2025, 4:58:14 PM
    Author     : loan1
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="dto.Alert" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Alert</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <nav class="navbar">
        <div class="left-menu">
            <a href="stockList.jsp">Stock List</a>
            <a href="MainController?action=TransactionList">Transaction List</a>
            <a href="alertList.jsp">Alert List</a>
        </div>
        <div class="right-menu">
            <a href="MainController?action=Logout">Logout</a>
        </div>
    </nav>

    <main>
        <%
            Alert alert = (Alert) request.getAttribute("ALERT");
        %>

        <form action="MainController" method="POST" class="custom-form">
            <h2>Update Alert</h2>

            <input type="hidden" name="alertID" value="<%= alert.getAlertID() %>" />

            <label>Ticker:</label>
            <input type="text" name="ticker" value="<%= alert.getTicker() %>" required />

            <label>Threshold:</label>
            <input type="number" name="threshold" value="<%= alert.getThreshold() %>" step="0.01" required />

            <label>Direction:</label>
<select name="direction" required>
    <option value="increase">Increase</option>
    <option value="decrease">Decrease</option>
</select>

            <label>Status:</label>
            <select name="status">
                <option value="inactive" <%= "inactive".equals(alert.getStatus()) ? "selected" : "" %>>Inactive</option>
                <option value="active" <%= "active".equals(alert.getStatus()) ? "selected" : "" %>>Active</option>
            </select>

            <button type="submit" name="action" value="UpdateAlert">Update</button>
        </form>
    </main>
</body>
</html>
