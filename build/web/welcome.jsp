<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*, javax.servlet.*" %>
<%
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("LOGIN_USER") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    dto.User loginUser = (dto.User) session.getAttribute("LOGIN_USER");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h2>🎉 welcome <%= loginUser.getFullName() %>!</h2>

    <ul>
        <li><a href="stockList.jsp"> Stock manager (stockList)</a></li>
        <li><a href="logout"> logout</a></li>
    </ul>
</body>
</html>
