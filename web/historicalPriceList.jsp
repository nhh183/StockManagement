<%-- 
    Document   : historicalPriceList
    Created on : Jun 11, 2025, 9:40:07 PM
    Author     : loan1
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="dto.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.HistoricalPrice" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Historical Price List</title>
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

    String fromDate = request.getParameter("fromDate") != null ? request.getParameter("fromDate") : "";
    String toDate = request.getParameter("toDate") != null ? request.getParameter("toDate") : "";
%>

<!-- Navbar -->
<div class="navbar">
    <div class="left-menu">
        <a href="MainController?action=search&search=">Stock List</a>
        <a href="MainController?action=TransactionList">Transaction List</a>
        <a href="MainController?action=AlertList">Alert List</a>
        <a href="MainController?action=HistoricalPriceList">Historical Prices</a>
    </div>
    <div class="right-menu">
        <a href="MainController?action=Logout">Logout</a>
    </div>
</div>

<div class="container">
    <h2>Welcome: <%= loginUser.getFullName() %></h2>

    <% if (MSG != null) { %>
        <p class="msg-success"><%= MSG %></p>
    <% } else if (ERROR != null) { %>
        <p class="msg-error"><%= ERROR %></p>
    <% } %>

    <form action="MainController" method="GET" style="margin-bottom: 15px;">
    <input type="hidden" name="action" value="SearchHistoricalPrice" />
    From Date: <input type="date" name="fromDate" value="<%= fromDate %>" required />
    To Date: <input type="date" name="toDate" value="<%= toDate %>" required />
    
    <button type="submit">Search</button>
    
    <a href="MainController?action=HistoricalPriceList" 
       style="margin-left: 10px; padding: 6px 12px; background-color: gray; color: white; text-decoration: none; border-radius: 5px;">
        Cancel
    </a>
</form>

    <c:if test="${not empty historicalPriceList}">
        <table border="1" width="100%">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Ticker</th>
                    <th>Date</th>
                    <th>Open</th>
                    <th>Close</th>
                    <th>High</th>
                    <th>Low</th>
                    <th>Function</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="hp" items="${historicalPriceList}" varStatus="loop">
                    <tr>
                        <td>${loop.count}</td>
                        <td>${hp.ticker}</td>
                        <td>${hp.date}</td>
                        <td>${hp.open}</td>
                        <td>${hp.close}</td>
                        <td>${hp.high}</td>
                        <td>${hp.low}</td>
                        <td>
                            <a href="MainController?action=UpdateHistoricalPrice&ticker=${hp.ticker}&date=${hp.date}">Update</a> /
                            <a href="MainController?action=DeleteHistoricalPrice&ticker=${hp.ticker}&date=${hp.date}" 
                               onclick="return confirm('Do you want to delete this record?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty historicalPriceList}">
        <p>No historical prices found.</p>
    </c:if>

    <a href="addHistoricalPrice.jsp" class="btn-add">Add New Historical Price</a>
</div>
</body>
</html>
