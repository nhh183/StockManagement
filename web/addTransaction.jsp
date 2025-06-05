<%-- 
    Document   : addTransaction
    Created on : Jun 5, 2025, 8:04:47 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add TRansaction Page</title>
    </head>
    <body>
        <h2>Add Transaction</h2>
        <% if (request.getAttribute("ERROR") != null) { %>
        <p style="color:red"><%= request.getAttribute("ERROR") %></p>
        <% } %>
        <form action="MainController" method="POST">
            Ticker: <input type="text" name="ticker" required><br>
            Type: <select name="type">
                <option value="buy">Buy</option>
                <option value="sell">Sell</option>
            </select><br>
            Quantity: <input type="number" name="quantity" required><br>
            Price: <input type="number" name="price" step="0.01" required><br>
            Status: <select name="status">
                <option value="pending">Pending</option>
                <option value="executed">Executed</option>
            </select><br>
            <input type="submit" name="action" value="createTransaction"><br/>
            <a href="transactionList.jsp">Back to Transaction List</a>
        </form>
    </body>
</html>