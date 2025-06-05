<%-- 
    Document   : updateAlert
    Created on : Jun 5, 2025, 1:53:49 PM
    Author     : loan1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.Alerts"%>
<!DOCTYPE html>
<html>
<head><title>Update Alert</title></head>
<body>

<%
    Alerts alert = (Alerts) request.getAttribute("ALERT");
    if (alert == null) {
        response.sendRedirect("SearchAlertController");
        return;
    }
%>

<h2>Update Alert</h2>

<% if (request.getAttribute("ERROR") != null) { %>
    <p style="color:red"><%= request.getAttribute("ERROR") %></p>
<% } %>

<form action="UpdateAlertController" method="POST">
    ID: <input type="text" name="alertID" value="<%= alert.getAlertID() %>" readonly><br>
    Ticker: <input type="text" name="ticker" value="<%= alert.getTicker() %>" readonly><br>
    Threshold: <input type="number" name="threshold" value="<%= alert.getThreshold() %>" step="0.01" required><br>
    Direction:
    <select name="direction">
        <option value="increase" <%= "increase".equals(alert.getDirection()) ? "selected" : "" %>>Increase</option>
        <option value="decrease" <%= "decrease".equals(alert.getDirection()) ? "selected" : "" %>>Decrease</option>
    </select><br>
    Status:
    <select name="status">
        <option value="inactive" <%= "inactive".equals(alert.getStatus()) ? "selected" : "" %>>Inactive</option>
        <option value="active" <%= "active".equals(alert.getStatus()) ? "selected" : "" %>>Active</option>
        <option value="pending" <%= "pending".equals(alert.getStatus()) ? "selected" : "" %>>Pending</option>
    </select><br>
    <input type="submit" value="Update">
</form>

<a href="SearchAlertController">Back to List</a>
</body>
</html>
