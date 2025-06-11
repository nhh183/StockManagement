<%-- 
    Document   : alertList
    Created on : Jun 8, 2025, 4:59:14 PM
    Author     : loan1
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.Alert" %>
<%@ page import="dto.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Alert List</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<%
    User loginUser = (User) session.getAttribute("USER");
    if (loginUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String MSG = (String) request.getAttribute("MSG");
    if (MSG == null) {
        MSG = (String) session.getAttribute("MSG");
        session.removeAttribute("MSG");
    }

    String ERROR = (String) request.getAttribute("ERROR");
    if (ERROR == null) {
        ERROR = (String) session.getAttribute("ERROR");
        session.removeAttribute("ERROR");
    }

    String keyword = request.getParameter("keyword") != null ? request.getParameter("keyword") : "";
    String filterDirection = request.getParameter("filterDirection") != null ? request.getParameter("filterDirection") : "";
    String filterStatus = request.getParameter("filterStatus") != null ? request.getParameter("filterStatus") : "";
%>

<nav class="navbar">
    <div class="left-menu">
        <a href="MainController?action=search&search=">Stock List</a>
        <a href="MainController?action=TransactionList">Transaction List</a>
        <a href="MainController?action=AlertList">Alert List</a>
    </div>
    <div class="right-menu">
        <a href="MainController?action=Logout">Logout</a>
    </div>
</nav>

<div class="container">
    <h2>Welcome: <%= loginUser.getFullName() %></h2>

    <% if (MSG != null) { %>
        <p class="msg-success"><%= MSG %></p>
    <% } else if (ERROR != null) { %>
        <p class="msg-error"><%= ERROR %></p>
    <% } %>

    <!-- Search + Filter Form -->
    <form action="MainController" method="GET" style="margin-bottom: 15px;">
        <input type="text" name="keyword" value="<%= keyword %>" placeholder="Search keyword..." />

        <select name="filterDirection">
            <option value="">--Direction--</option>
            <option value="increase" <%= "increase".equals(filterDirection) ? "selected" : "" %>>Increase</option>
            <option value="decrease" <%= "decrease".equals(filterDirection) ? "selected" : "" %>>Decrease</option>
        </select>

        <select name="filterStatus">
            <option value="">--Status--</option>
            <option value="active" <%= "active".equals(filterStatus) ? "selected" : "" %>>Active</option>
            <option value="inactive" <%= "inactive".equals(filterStatus) ? "selected" : "" %>>Inactive</option>
            <option value="pending" <%= "pending".equals(filterStatus) ? "selected" : "" %>>Pending</option>
        </select>

        <button type="submit" name="action" value="SearchAlert">Search</button>
    </form>

    <%
        ArrayList<Alert> list = (ArrayList<Alert>) request.getAttribute("alertList");
        if (list != null && !list.isEmpty()) {
    %>
    <table border="1">
        <thead>
            <tr>
                <th>No</th>
                <th>Ticker</th>
                <th>Threshold</th>
                <th>Direction</th>
                <th>Status</th>
                <th>Function</th>
            </tr>
        </thead>
        <tbody>
            <%
                int count = 0;
                for (Alert alert : list) {
                    count++;
            %>
            <tr>
                <td><%= count %></td>
                <td><%= alert.getTicker() %></td>
                <td><%= alert.getThreshold() %></td>
                <td><%= alert.getDirection() %></td>
                <td><%= alert.getStatus() %></td>
                <td>
                    <%
                        boolean canEdit = loginUser.getUserID().equals(alert.getUserID()) &&
                                          "inactive".equalsIgnoreCase(alert.getStatus());
                        if ("AD".equals(loginUser.getRoleID()) || canEdit) {
                    %>
                    <% if ("inactive".equals(alert.getStatus())) { %>
                        <a href="MainController?action=UpdateAlert&id=<%= alert.getAlertID() %>">Update</a> /
                    <% } %>
                    <a href="MainController?action=DeleteAlert&id=<%= alert.getAlertID() %>"
                       onclick="return confirm('Delete this alert?')">Delete</a>
                    <% } else { %>
                    N/A
                    <% } %>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <% } else { %>
        <p>No alerts found.</p>
    <% } %>

    <a href="addAlert.jsp" class="btn-add">Add New Alert</a>
</div>
</body>
</html>
