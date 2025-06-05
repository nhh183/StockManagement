<%-- 
    Document   : transactionList
    Created on : Jun 5, 2025, 11:23:26 AM
    Author     : User
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dto.TransactionDTO" %>
<%@page import="dto.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction List Page</title>
    </head>
    <body>
        <%
            User loginUser = (User) session.getAttribute("LOGIN_USER");
            if (loginUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h1>Welcome: <%= loginUser.getFullName() %></h1>

        <form action="MainController" method="POST">
            <button type="submit" name="action" value="Logout">Logout</button>
            <input type="text" name="keyword" placeholder="Search"/>
            <button type="submit" name="action" value="SearchTransaction">Search</button>
        </form>

        <a href="createTransaction.jsp">Create New Transaction</a><br/>
        <a href="stockList.jsp">Go to Stock List</a><br/>
        <a href="alertList.jsp">Go to Alert List</a><br/>

        <!-- Display messages -->
        <%
            String MSG = (String) request.getAttribute("MESSAGE");
            String ERROR = (String) request.getAttribute("ERROR");
            if (MSG != null) {
        %>
        <p style="color: green"><%= MSG %></p>
        <% } else if (error != null) { %>
        <p style="color: red"><%= ERROR %></p>
        <% } %>

        <%
            ArrayList<TransactionDTO> list = (ArrayList<TransactionDTO>) request.getAttribute("list");
            if (list != null && !list.isEmpty()) {
        %>
        <table border="1">
            <tr>
                <th>No</th>
                <th>User ID</th>
                <th>Ticker</th>
                <th>Type</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Status</th>
                    <% if ("AD".equals(loginUser.getRoleID())) { %>
                <th>Function</th>
                    <% } %>
            </tr>
            <%
                int count = 0;
                for (TransactionDTO transaction : list) {
                    count++;
            %>
            <tr>
                <td><%= count %></td>
                <td><%= transaction.getUserID() %></td>
                <td><%= transaction.getTicker() %></td>
                <td><%= transaction.getType() %></td>
                <td><%= transaction.getQuantity() %></td>
                <td><%= transaction.getPrice() %></td>
                <td><%= transaction.getStatus() %></td>
                <% if ("AD".equals(loginUser.getRoleID()) || loginUser.getUserID().equals(transaction.getUserID())) { %>
                <td>
                    <a href="MainController?action=updateTransactionPageController&id=<%= transaction.getId() %>">Update</a>
                    <a href="deleteTransactionController?id=<%= transaction.getId() %>">Delete</a>
                </td>
                <% } %>
            </tr>
            <% } %>
        </table>
        <% } else { %>
        <p>No transactions found.</p>
        <% } %>

    </body>
</html>

