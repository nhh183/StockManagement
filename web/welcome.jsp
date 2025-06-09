<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*, javax.servlet.*, dto.User" %>
<%
    if (session == null || session.getAttribute("USER") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    User loginUser = (User) session.getAttribute("USER");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Welcome</title>
    </head>
    <body>
        <h2>Welcome <%= loginUser.getFullName() %>!</h2>
        <form action="MainController" method="get">
            <div class="button">
                <button type="submit" name="action" value="TransactionList">View Transaction</button>
            </div>
        </form>

        <form action="MainController" method="get">
    <div class="button">
        <input type="submit" name="action" value="AlertList" />
    </div>
</form>

        <form action="MainController" method="get">
            <input type="hidden" name="action" value="SearchUser" />
            <button type="submit" class="button-green">User List</button>
        </form>
        <div class="stocks">
            <jsp:include page="stockList.jsp" />
        </div>
        <form action="MainController" method="get">
            <div class="button">
                <input type="submit" name="action" value="Logout">
            </div>
        </form>
    </body>
</html>
