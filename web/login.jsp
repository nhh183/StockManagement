<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>
    <h2>Sign in</h2>
    <% if(request.getAttribute("MSG") != null){ %>
        <p style="color: red;"><%= request.getAttribute("MSG") %></p>
    <% } %>
    <form action="LoginController" method="post">
        UserID: <input type="text" name="userID" required><br>
        Password: <input type="password" name="password" required><br>
        <input type="submit" name="action" value="Login">
    </form>
</body>
</html>
