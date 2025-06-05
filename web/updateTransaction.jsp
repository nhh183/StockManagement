<%-- 
    Document   : updateTransaction
    Created on : Jun 5, 2025, 1:15:40 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.TransactionDTO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Transaction Page</title>
    </head>
    <body>
        <%
            TransactionDTO transaction = (TransactionDTO) request.getAttribute("TRANSACTION");
            if (transaction == null) {
                response.sendRedirect("transactionList.jsp");
                return;
            }
        %>

        <h2>Update Transaction Information</h2>

        <% 
            String MSG = (String) request.getAttribute("MESSAGE");
            String ERROR = (String) request.getAttribute("ERROR");
            if (MSG != null) {
        %>
        <p style="color: green"><%= MSG %></p>
        <% } else if (ERROR != null) { %>
        <p style="color: red"><%= ERROR %></p>
        <% } %>

        <form action="MainController" method="POST">
            ID: <input type="text" name="id" value="<%= transaction.getId() %>" readonly/><br/>
            Ticker: <input type="text" name="ticker" value="<%= transaction.getTicker() %>" required/><br/>
            Type: 
            <select name="type">
                <option value="buy" <%= "buy".equals(transaction.getType()) ? "selected" : "" %>>Buy</option>
                <option value="sell" <%= "sell".equals(transaction.getType()) ? "selected" : "" %>>Sell</option>
            </select><br/>
            Quantity: <input type="number" name="quantity" value="<%= transaction.getQuantity() %>" required><br/>
            Price: <input type="number" name="price" value="<%= transaction.getPrice() %>" step="0.01" required><br/>
            Status: 
            <select name="status">
                <option value="pending" <%= "pending".equals(transaction.getStatus()) ? "selected" : "" %>>Pending</option>
                <option value="executed" <%= "executed".equals(transaction.getStatus()) ? "selected" : "" %>>Executed</option>
            </select><br/>
            <input type="submit" name="action" value="UpdateTransaction"/>
            <a href="transactionList.jsp">Back to Transaction List</a>
        </form>
    </body>
</html>

