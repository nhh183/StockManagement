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
        form {
            width: 400px;
            margin: 30px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
        }

        label {
            display: block;
            margin-top: 10px;
        }

        input, select {
            width: 100%;
            padding: 6px;
        }

        .btn {
            margin-top: 15px;
            padding: 8px 12px;
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
        <a href="welcome.jsp" class="btn">Cancel</a>
    </form>
</body>
</html>
>
</html>
