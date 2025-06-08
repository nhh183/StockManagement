
<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>
    <h2></h2>
    <% if(request.getAttribute("error") != null){ %>
        <p style="color: red;"><%= request.getAttribute("error")%></p>
    <%}%>
    <form action="MainController" method="post">
        UserID: <input type="text" name="userID" required><br>
        Password: <input type="password" name="password" required><br>
        <button type="submit" name="action" value="login">Login</button>
    </form>
</body>
</html>