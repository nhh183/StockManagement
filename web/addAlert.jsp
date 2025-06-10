<%-- 
    Document   : addAlert
    Created on : Jun 5, 2025, 1:48:55 PM
    Author     : loan1
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="dto.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Alert</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div>
        
    </div>
    
    
    
    
    
    
    
    
    
    
    
    
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
        <form action="MainController" method="POST" class="custom-form">
            <h2>Add New Alert</h2>

            <label>Ticker:</label>
            <input type="text" name="ticker" required />

            <label>Threshold:</label>
            <input type="number" name="threshold" step="0.01" required />

            <label>Direction:</label>
<select name="direction" required>
    <option value="increase">Increase</option>
    <option value="decrease">Decrease</option>
</select>


            <label>Status:</label>
            <select name="status" required>
                <option value="inactive">Inactive</option>
                <option value="active">Active</option>
            </select>

            <button type="submit" name="action" value="CreateAlert">Create</button>
        </form>
    </main>
</body>
</html>
