<%-- 
    Document   : addHistoricalPrice
    Created on : Jun 10, 2025, 10:41:20 AM
    Author     : loan1
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="dto.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Historical Price</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        main {
            max-width: 600px;
            margin: 50px auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
        }

        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }

        input[type="text"], input[type="date"], input[type="number"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            margin-top: 20px;
            width: 100%;
            padding: 10px;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>    
    <nav class="navbar">
        <div class="left-menu">
            <a href="MainController?action=search&search=">Stock List</a>
            <a href="MainController?action=TransactionList">Transaction List</a>
            <a href="MainController?action=AlertList">Alert List</a>
            <a href="MainController?action=HistoricalPriceList">Historical Prices</a>
        </div>
        <div class="right-menu">
            <a href="MainController?action=Logout">Logout</a>
        </div>
    </nav>

    <main>
        <form action="MainController" method="POST" class="custom-form">
            <h2>Add New Historical Price</h2>

            <label>Ticker:</label>
            <input type="text" name="ticker" required />

            <label>Date:</label>
            <input type="date" name="date" required />

            <label>Open:</label>
            <input type="number" name="open" step="0.01" min="0" required />

            <label>Close:</label>
            <input type="number" name="close" step="0.01" min="0" required />

            <label>High:</label>
            <input type="number" name="high" step="0.01" min="0" required />

            <label>Low:</label>
            <input type="number" name="low" step="0.01" min="0" required />

            <button type="submit" name="action" value="CreateHistoricalPrice">Create</button>
        </form>
    </main>
</body>
</html>
