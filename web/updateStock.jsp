<%-- 
    Document   : updateStonk
    Created on : Jun 5, 2025, 3:49:26 PM
    Author     : NHH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="dto.Stock" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Stock</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
        }

        h2 {
            text-align: center;
            margin-top: 30px;
            color: #2c3e50;
        }

        form {
            width: 400px;
            margin: 30px auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.05);
        }

        label {
            display: block;
            margin-top: 12px;
            font-weight: bold;
        }

        input, select {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .form-buttons {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
        }

        .btn {
            padding: 10px 20px;
            border: none;
            color: white;
            background-color: #3498db;
            text-decoration: none;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
        }

        .btn:hover {
            background-color: #219150;
        }

        .btn.cancel {
            background-color: #7f8c8d;
        }

        .btn.cancel:hover {
            background-color: #636e72;
        }
    </style>
</head>
<body>
    <h2 style="text-align:center;">Update Stock</h2>
    <%
        Stock stock = (Stock) request.getAttribute("stock");

    %>
    <form action="MainController" method="post">
        <label>Ticker</label>
        <input type="text" name="ticker" value="<%= stock.getTicker() %>" readonly />
        <input type="text" name="name" value="${stock.name}" required />
        <input type="text" name="sector" value="${stock.sector}" required />
        <input type="number" step="0.01" min="0.01" name="price" value="${stock.price}" required />
        <select name="status">
            <option value="true" ${stock.status ? "selected" : ""}>Active</option>
            <option value="false" ${!stock.status ? "selected" : ""}>Inactive</option>
        </select>

        <input type="submit" class="btn" name="action" value="update" />
    </form>
</body>
</html>
>
</html>
