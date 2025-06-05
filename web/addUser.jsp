<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head><title>Add USER</title></head>
<body>
    <h2>Add User</h2>
    <form action="addUser" method="post">
        UserID: <input type="text" name="userID"><br>
        FullName: <input type="text" name="fullName"><br>
        RoleID: <input type="text" name="roleID"><br>
        Password: <input type="password" name="password"><br>
        <input type="submit" value="Thêm">
    </form>
    <p style="color:red">${requestScope.error}</p>
    <a href="userList.jsp">back</a>
</body>
</html>
