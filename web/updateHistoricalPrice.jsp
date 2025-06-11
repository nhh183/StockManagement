<%-- 
    Document   : updateHistoricalPrice
    Created on : Jun 11, 2025, 9:40:49 PM
    Author     : loan1
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.HistoricalPrice" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Historical Price</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        main {
            width: 400px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }
        input {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        button {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>

    <!-- Giữ nguyên navbar -->
    <nav class="navbar">
        <div class="left-menu">
            <a href="stockList.jsp">Stock List</a>
            <a href="MainController?action=TransactionList">Transaction List</a>
            <a href="MainController?action=AlertList">Alert List</a>
            <a href="MainController?action=HistoricalPriceList">Historical Prices</a>
        </div>
        <div class="right-menu">
            <a href="MainController?action=Logout">Logout</a>
        </div>
    </nav>

    <main>
        <%
            HistoricalPrice price = (HistoricalPrice) request.getAttribute("PRICE");
        %>

        <form action="MainController" method="POST">
            <h2>Update Historical Price</h2>

            <label>Ticker:</label>
            <input type="text" name="ticker" value="<%= price.getTicker() %>" readonly />

            <label>Date:</label>
            <input type="date" name="date" value="<%= price.getDate() %>" readonly />

            <label>Open:</label>
            <input type="number" name="open" step="0.01" value="<%= price.getOpen() %>" required />

            <label>Close:</label>
            <input type="number" name="close" step="0.01" value="<%= price.getClose() %>" required />

            <label>High:</label>
            <input type="number" name="high" step="0.01" value="<%= price.getHigh() %>" required />

            <label>Low:</label>
            <input type="number" name="low" step="0.01" value="<%= price.getLow() %>" required />

            <button type="submit" name="action" value="UpdateHistoricalPrice">Update</button>
        </form>
    </main>

</body>
</html>

