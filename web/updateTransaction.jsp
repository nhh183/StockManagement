<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.Transaction" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Transaction Page</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>

        <!-- Giả sử có navbar ở đây nếu cần -->
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
            <%
                Transaction transaction = (Transaction) request.getAttribute("TRANSACTION");
            %>

            <form action="MainController" method="POST" class="custom-form">
                <h2>Update Transaction Information</h2>

                <% if (request.getAttribute("ERROR") != null) { %>
                <div class="msg-error"><%= request.getAttribute("ERROR") %></div>
                <% } %>

                <label for="id">ID:</label>
                <input type="text" id="id" name="id" value="<%= transaction.getId() %>" readonly />

                <label for="ticker">Ticker:</label>
                <input type="text" id="ticker" name="ticker" value="<%= transaction.getTicker() %>" required />

                <label for="type">Type:</label>
                <select id="type" name="type" required>
                    <option value="buy" <%= "buy".equals(transaction.getType()) ? "selected" : "" %>>Buy</option>
                    <option value="sell" <%= "sell".equals(transaction.getType()) ? "selected" : "" %>>Sell</option>
                </select>

                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" value="<%= transaction.getQuantity() %>" required />

                <label for="price">Price:</label>
                <input type="number" id="price" name="price" value="<%= transaction.getPrice() %>" step="0.01" required />

                <label for="status">Status:</label>
                <select id="status" name="status" required>
                    <option value="pending" <%= "pending".equals(transaction.getStatus()) ? "selected" : "" %>>Pending</option>
                    <option value="executed" <%= "executed".equals(transaction.getStatus()) ? "selected" : "" %>>Executed</option>
                </select>

                <button type="submit" name="action" value="UpdateTransaction">Update</button>
            </form>
        </main>

    </body>
</html>
