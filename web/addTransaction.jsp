<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Add Transaction Page</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>

        <!-- Navbar giống như trang update -->
        <nav class="navbar">
            <div class="left-menu">
                <a href="MainController?action=TransactionList">Transaction List</a>
                <a href="stockList.jsp">Stock List</a>
                <a href="alertList.jsp">Alert List</a>
            </div>
            <div class="right-menu">
                <a href="MainController?action=Logout">Logout</a>
            </div>
        </nav>

        <main>
            <% if (request.getAttribute("ERROR") != null) { %>
            <div class="msg-error"><%= request.getAttribute("ERROR") %></div>
            <% } %>

            <form action="MainController" method="POST" class="custom-form">
                <h2>Add New Transaction</h2>

                <label for="ticker">Ticker:</label>
                <input type="text" id="ticker" name="ticker" required />

                <label for="type">Type:</label>
                <select id="type" name="type" required>
                    <option value="buy">Buy</option>
                    <option value="sell">Sell</option>
                </select>

                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" min="1" required />

                <label for="price">Price:</label>
                <input type="number" id="price" name="price" min="0.01" step="0.01" required />

                <label for="status">Status:</label>
                <select id="status" name="status" required>
                    <option value="pending">Pending</option>
                    <option value="executed">Executed</option>
                </select>

                <button type="submit" name="action" value="CreateTransaction">Create</button>
            </form>
        </main>
    </body>
</html>
