<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dto.User"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User List</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            background-color: #f4f6f8;
        }
        .container {
            display: flex;
            min-height: 100vh;
        }
        .sidebar {
            width: 220px;
            background-color: #2c3e50;
            color: #fff;
            padding: 20px;
        }
        .sidebar h2 {
            margin-bottom: 20px;
        }
        .sidebar a {
            color: #ccc;
            text-decoration: none;
            display: block;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        .sidebar a:hover,
        .sidebar a.active {
            background-color: #34495e;
            color: #fff;
        }
        .main-content {
            flex: 1;
            padding: 30px;
            background-color: #fff;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .header a {
            background-color: #e74c3c;
            color: white;
            padding: 6px 12px;
            border-radius: 4px;
            text-decoration: none;
        }
        .header a:hover {
            background-color: #c0392b;
        }
        form {
            margin: 20px 0;
        }
        input, select {
            padding: 6px;
            margin: 5px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }
        .inputSearch {
            width: 200px;
        }
        .searchBtn, .button-green, .button-red {
            padding: 6px 10px;
            border: none;
            border-radius: 4px;
            color: white;
            cursor: pointer;
            transition: 0.3s;
        }
        .searchBtn {
            background-color: #3498db;
        }
        .button-green {
            background-color: #2ecc71;
        }
        .button-red {
            background-color: #e74c3c;
        }
        .searchBtn:hover {
            background-color: #2980b9;
        }
        .button-green:hover {
            background-color: #27ae60;
        }
        .button-red:hover {
            background-color: #c0392b;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #3498db;
            color: white;
        }
        .actions button {
            margin-right: 5px;
        }
        .msg {
            margin-top: 10px;
            padding: 10px;
            border-radius: 4px;
        }
        .success {
            color: #155724;
            background-color: #d4edda;
        }
        .error {
            color: #721c24;
            background-color: #f8d7da;
        }
    </style>
</head>
<body>
<%
    String search = request.getParameter("search");
    if (search == null) {
        search = "";
    }
    User loginUser = (User) session.getAttribute("USER");
    if (loginUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<div class="container">
    <div class="sidebar">
        <h2>Menu</h2>
        <a href="MainController?action=SearchStock">Stock List</a>
        <a href="MainController?action=SearchTransaction">Transaction List</a>
        <a href="MainController?action=ViewAlerts">Alert List</a>
        <% if ("AD".equals(loginUser.getRoleID())) { %>
            <a class="active" href="MainController?action=SearchUser">User List</a>
        <% } %>
    </div>

    <div class="main-content">
        <div class="header">
            <h1>Welcome, <c:out value="${sessionScope.LOGIN_USER.fullName}"/></h1>
            <a href="${pageContext.request.contextPath}/LogoutController">Logout</a>
        </div>

        <hr>

        <div class="function-header">
            <div class="function">
                <form action="MainController">
                    Search 
                    <input type="text" class="inputSearch" name="search" value="<%= search %>" placeholder="Search"/>
                    <button type="submit" class="searchBtn" name="action" value="SearchUser">Search</button>
                </form>

                <button id="showCreateForm" class="button-green" onclick="toggleCreateForm()">Create</button>

                <div id="createForm" style="display: none;">
                    <h3>Create New User</h3><hr>
                    <form action="MainController" method="POST">
                        <input type="hidden" name="action" value="CreateUser"/>
                        <div class="form-group">
                            <label for="userID">User ID:</label>
                            <input type="text" id="userID" name="userID" required>
                        </div>
                        <div class="form-group">
                            <label for="fullName">Full Name:</label>
                            <input type="text" id="fullName" name="fullName" required>
                        </div>
                        <div class="form-group">
                            <label for="roleID">Role ID:</label>
                            <select id="roleID" name="roleID" required>
                                <option value="AD">Admin</option>
                                <option value="NV">Staff</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" id="password" name="password" required>
                        </div>
                        <button type="submit" class="button-green">Create</button>
                    </form>
                </div>

                <c:if test="${empty listUser}">
                    <p style="margin: 10px 0 0;">No matching users found!</p>
                </c:if>
            </div>
            <div class="message">
                <%
                    String MSG = (String) request.getAttribute("MSG");
                    if ((MSG != null && MSG.contains("successfully")) || (MSG != null && MSG.contains("Successfully"))) {
                %>
                <h3 id="msg" class="msg success"><%= MSG %></h3>
                <% } else if (MSG != null) { %>
                <h3 id="msg" class="msg error"><%= MSG %></h3>
                <% } %>
            </div>
        </div>

        <table>
            <thead>
                <tr>
                    <th>No</th><th>User ID</th><th>Full Name</th><th>Role ID</th><th>Password</th><th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${listUser}" varStatus="st">
                    <tr>
                        <form action="MainController" method="POST">
                            <input type="hidden" name="search" value="<%= search %>"/>
                            <td>${st.count}</td>
                            <td>
                                <input type="hidden" name="userID" value="${user.userID}"/>
                                ${user.userID}
                            </td>
                            <td>${user.fullName}</td>
                            <td>${user.roleID}</td>
                            <td>${user.password}</td>
                            <td class="actions">
                                <button type="submit" name="action" value="UpdateUser">Update</button>
                                <button class="butDelete" type="submit" name="action" value="DeleteUser" onclick="return confirm('Are you sure to delete this user?')">Delete</button>
                            </td>
                        </form>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script>
    function toggleCreateForm() {
        const form = document.getElementById("createForm");
        const btn = document.getElementById("showCreateForm");
        if (form.style.display === "none") {
            form.style.display = "block";
            btn.classList.remove("button-green");
            btn.classList.add("button-red");
            btn.innerText = "Close";
        } else {
            form.style.display = "none";
            btn.classList.remove("button-red");
            btn.classList.add("button-green");
            btn.innerText = "Create";
        }
    }

    window.addEventListener("DOMContentLoaded", () => {
        const msg = document.getElementById("msg");
        if (msg) {
            setTimeout(() => {
                msg.style.opacity = "0";
                setTimeout(() => msg.style.display = "none", 500);
            }, 3000);
        }
    });
</script>
</body>
</html>
