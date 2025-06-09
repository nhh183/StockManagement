<%-- 
    Document   : updateAlert
    Created on : Jun 8, 2025, 4:58:14 PM
    Author     : loan1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.Alert"%>
<%
    Alert alert = (Alert) request.getAttribute("alert");
%>
<h2>Update Alert</h2>
<form action="UpdateAlertController" method="post">
    <input type="hidden" name="alertID" value="<%= alert.getAlertID() %>">
    <label>Ticker</label>
    <input type="text" value="<%= alert.getTicker() %>" readonly><br>
    <label>Threshold</label>
    <input type="number" step="0.01" name="threshold" value="<%= alert.getThreshold() %>"><br>
    <label>Direction</label>
    <select name="direction">
        <option value="increase" <%= "increase".equals(alert.getDirection()) ? "selected" : "" %>>Increase</option>
        <option value="decrease" <%= "decrease".equals(alert.getDirection()) ? "selected" : "" %>>Decrease</option>
    </select><br>
    <label>Status</label>
    <select name="status">
        <option value="inactive" <%= "inactive".equals(alert.getStatus()) ? "selected" : "" %>>Inactive</option>
        <option value="active" <%= "active".equals(alert.getStatus()) ? "selected" : "" %>>Active</option>
        <option value="pending" <%= "pending".equals(alert.getStatus()) ? "selected" : "" %>>Pending</option>
    </select><br>
    <input type="submit" value="Update">
    <a href="alertList.jsp">Cancel</a>

</form>
