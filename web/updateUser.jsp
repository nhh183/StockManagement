<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="dto.User" %>
<%
    User user = (User) request.getAttribute("user");
%>
<html>
<head><title>update user</title></head>
<body>
    <h2>update User: <%= user.getUserID() %></h2>
    <form action="updateUser" method="post">
        <input type="hidden" name="userID" value="<%= user.getUserID() %>">
        FullName: <input type="text" name="fullName" value="<%= user.getFullName() %>"><br>
        RoleID: <input type="number" name="roleID" value="<%= user.getRoleID() %>"><br>
        Password: <input type="password" name="password"><br>
        <input type="submit" value="Cập nhật">
    </form>
    <a href="userList.jsp">back</a>
</body>
</html>
