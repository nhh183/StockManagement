<%-- 
    Document   : transactionList
    Created on : Jun 5, 2025, 11:23:26 AM
    Author     : User
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dto.Transaction" %>
<%@page import="dto.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction List Page</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">

    </head>
    <body>
        <%
            User loginUser = (User) session.getAttribute("USER");
            if (loginUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>

        <!-- Thanh Menu -->
        <div class="navbar">
            <div class="left-menu">
                <a href="MainController?action=search&search">Stock List</a>
                <a href="MainController?action=SearchAlert">Alert List</a>
                <a href="MainController?action=SearchUser">User List</a>
            </div>
            <div class="right-menu">
                <a href="MainController?action=Logout">Logout</a>
            </div>
        </div>

        <!-- Lời chào căn giữa -->
        <div class="welcome-content">
            <h1>Welcome: <%= loginUser.getFullName() %></h1>
        </div>
        
        <div class="container">
            <div class="center-content"><h2>Transaction List</h2></div>
            <div class="search-box">
                <form action="MainController" method="POST" style="margin:0;">
                    <input type="text" name="keyword" placeholder="Search"/>
                    <button type="submit" name="action" value="SearchTransaction">Search</button>
                </form>
            </div>

            <!-- Hiển thị thông báo -->
            <%
                String MSG = (String) session.getAttribute("MSG");
                String ERROR = (String) session.getAttribute("ERROR");

                session.removeAttribute("MSG");
                session.removeAttribute("ERROR");
                if (MSG != null) {
            %>
            <p class="msg-success"><%= MSG %></p>
            <% } else if (ERROR != null) { %>
            <p class="msg-error"><%= ERROR %></p>
            <% } %>

            <%
                ArrayList<Transaction> list = (ArrayList<Transaction>) request.getAttribute("transactionList");
                if (list != null && !list.isEmpty()) {
            %>
            <table class="transaction-table">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>User ID</th>
                        <th>Ticker</th>
                        <th>Type</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Status</th>                          
                        <th>Function</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (Transaction transaction : list) {
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
                            <a href="MainController?action=UpdateTransaction&id=<%= transaction.getId() %>">Update</a> /
                            <a href="MainController?action=DeleteTransaction&id=<%= transaction.getId() %>" 
                               onclick="return confirm('Are you sure you want to delete this transaction?')">Delete</a>
                        </td>
                        <% } %>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% } else { %>
            <p class="no-transaction">No transactions found.</p>
            <% } %>
            <a href="addTransaction.jsp" class="btn-add">Add</a>
        </div>

    </body>


</html>


