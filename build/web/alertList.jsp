<%-- 
    Document   : alertList
    Created on : Jun 5, 2025, 1:55:05 PM
    Author     : loan1
--%>

<%@page import="dto.Alerts"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Alert List</title></head>
<body>

<%
    User loginUser = (User) session.getAttribute("USER");
    if (loginUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<h1>Welcome: <%= loginUser.getFullName() %></h1>

<form action="MainController" method="POST">
    <button type="submit" name="action" value="Logout">Logout</button><br>
    <input type="text" name="search" placeholder="Search">
    <button type="submit" name="action" value="SearchAlert">Search</button>
</form>

<a href="addAlert.jsp">Create New Alert</a><br>
<a href="MainController?action=TransactionList">Transaction List</a><br>
<a href="stockList.jsp">Stock List</a><br>

<%
    String MSG = (String) request.getAttribute("MSG");
    if (MSG != null) {
%>
    <h3><%= MSG %></h3>
<% } %>

<%
    ArrayList<Alerts> list = (ArrayList<Alerts>) request.getAttribute("list");
    if (list != null && !list.isEmpty()) {
%>
    <table border="1">
        <tr>
            <th>No</th>
            <th>User ID</th>
            <th>Ticker</th>
            <th>Threshold</th>
            <th>Direction</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <%
            int count = 0;
            for (Alerts alert : list) {
                count++;
        %>
        <tr>
            <td><%= count %></td>
            <td><%= alert.getUserID() %></td>
            <td><%= alert.getTicker() %></td>
            <form action="MainController" method="POST">
                <td><input type="number" name="threshold" value="<%= alert.getThreshold() %>" step="0.01"></td>
                <td><%= alert.getDirection() %></td>
                <td>
                    <select name="status">
                        <option value="active" <%= "active".equals(alert.getStatus()) ? "selected" : "" %>>active</option>
                        <option value="inactive" <%= "inactive".equals(alert.getStatus()) ? "selected" : "" %>>inactive</option>
                    </select>
                </td>
                <td>
                    <input type="hidden" name="alertID" value="<%= alert.getAlertID() %>">
                    <button type="submit" name="action" value="UpdateAlert">Update</button>
                    <% if ("inactive".equals(alert.getStatus())) { %>
                    <button type="submit" name="action" value="DeleteAlert"
                            onclick="return confirm('Are you sure to delete this alert?')">Delete</button>
                    <% } %>
                </td>
            </form>
        </tr>
        <% } %>
    </table>
<% } else { %>
    <p>No alerts found.</p>
<% } %>

</body>
</html>
