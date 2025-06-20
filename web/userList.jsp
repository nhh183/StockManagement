<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dto.User"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User List</title>
    <style>
        /* Reset & base styles */
body {
    margin: 0;
    font-family: Arial, sans-serif;
    background-color: #f4f9ff;
}

/* Layout */
.container {
    display: flex;
    height: 100vh;
}

/* Sidebar */
.sidebar {
    width: 250px;
    background-color: #3498db;
    color: white;
    padding: 20px;
    box-sizing: border-box;
}

.sidebar h2 {
    margin-top: 0;
}

.sidebar a {
    display: block;
    color: white;
    text-decoration: none;
    padding: 10px 0;
    font-weight: bold;
    border-bottom: 1px solid rgba(255, 255, 255, 0.2);
}

.sidebar a.active,
.sidebar a:hover {
    background-color: #2980b9;
    padding-left: 10px;
}

/* Main content */
.main-content {
    flex: 1;
    padding: 20px;
    box-sizing: border-box;
    background-color: #fff;
    overflow-y: auto;
}

/* Header */
.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.header a {
    background-color: #e74c3c;
    color: white;
    padding: 8px 12px;
    text-decoration: none;
    border-radius: 4px;
}

.header a:hover {
    background-color: #c0392b;
}

/* Table styles */
table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 15px;
}

th, td {
    border: 1px solid #ddd;
    padding: 10px;
    text-align: left;
}

thead {
    background-color: #ecf6fd;
    font-weight: bold;
}

tbody tr:hover {
    background-color: #f4f4f4;
}

/* Form styles */
form input[type="text"],
form input[type="password"],
form select {
    padding: 8px;
    margin: 5px 0;
    font-size: 14px;
    width: 100%;
    box-sizing: border-box;
}

form button {
    padding: 8px 14px;
    margin: 5px 0;
    font-size: 14px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.button-green {
    background-color: #2ecc71;
    color: white;
}

.button-red {
    background-color: #e74c3c;
    color: white;
}

form .actions button {
    margin-right: 8px;
}

.butDelete {
    background-color: #e74c3c;
    color: white;
}

/* Message feedback */
.msg.success {
    color: green;
}

.msg.error {
    color: red;
}

#msg {
    transition: opacity 0.5s ease-in-out;
}

/* Create user form */
#createForm {
    margin-top: 20px;
    padding: 15px;
    background-color: #f1faff;
    border: 1px solid #ccc;
    border-radius: 6px;
}

#createForm .form-group {
    margin-bottom: 10px;
}

#createForm label {
    display: block;
    font-weight: bold;
}

.inputSearch {
    padding: 6px;
    font-size: 14px;
    width: 200px;
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
