<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dto.User" %>
<%
    List<User> users = (List<User>) request.getAttribute("userList");
%>
<html>
    <head><title>User list</title></head>
    <body>
        <h2> Users List</h2>
        <form action="MainController" method="get">
            find User by ID: 
            <input type="text" name="keyword" value="<%= request.getParameter("keyword") != null ? request.getParameter("keyword") : "" %>">
            <button type="submit" name="action" value="SearchUser">Search</button>
        </form>
        <table border="1">
            <tr><th>UserID</th><th>FullName</th><th>RoleID</th><th>Action</th></tr>
                    <%
                        for (User user : users) {
                    %>
            <tr>
                <td><%= user.getUserID() %></td>
                <td><%= user.getFullName() %></td>
                <td><%= user.getRoleID() %></td>
                <td>
                    <a href="updateUser?userID=<%= user.getUserID() %>">Edit</a> |
                    <a href="deleteUser?userID=<%= user.getUserID() %>" onclick="return confirm('Are you sure to delete?')">Delete</a>
                </td>
            </tr>
            <% } %>
        </table>
        <a href="addUser.jsp">add User</a>
    </body>
</html>
