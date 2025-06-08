<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dao.UserDAO"%>
<%@page import="dto.User"%>

<%
    User loginUser = (User) session.getAttribute("USER");
    if (loginUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String userId = request.getParameter("userID");
    if (userId == null) {
        out.println("Invalid user ID.");
        return;
    }

    UserDAO userDAO = new UserDAO();
    User user = userDAO.getUserByID(userId);

    if (user == null) {
        out.println("User doesn't exist.");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Update User Page</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f0f4f8;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .form-container {
                background-color: #fff;
                padding: 30px 40px;
                border-radius: 12px;
                box-shadow: 0 4px 10px rgba(0,0,0,0.1);
                width: 400px;
            }

            h2 {
                text-align: center;
                margin-bottom: 25px;
                color: #333;
            }

            label {
                font-weight: 600;
                display: block;
                margin-top: 15px;
                margin-bottom: 5px;
                color: #555;
            }

            input[type="text"], select {
                width: 100%;
                padding: 10px 12px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 6px;
                box-sizing: border-box;
                transition: border-color 0.3s;
            }

            input[type="text"]:focus, select:focus {
                border-color: #007bff;
                outline: none;
            }

            button {
                background-color: #28a745;
                color: white;
                padding: 10px 15px;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                width: 100%;
                font-weight: bold;
                transition: background-color 0.3s;
            }

            button:hover {
                background-color: #218838;
            }

            a {
                display: block;
                text-align: center;
                margin-top: 15px;
                text-decoration: none;
                color: #007bff;
                font-weight: 500;
                transition: color 0.2s;
            }

            a:hover {
                color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="form-container">
            <h2>Update User</h2>
            <form action="UpdateUserController" method="POST">
                <input type="hidden" name="userID" value="<%= user.getUserID() %>" />
                
                <label>User ID</label>
                <input type="text" value="<%= user.getUserID() %>" disabled />

                <label>Full Name</label>
                <input type="text" name="fullName" value="<%= user.getFullName() %>" required />

                <label>Role ID</label>
                <select name="roleID" required>
                    <option value="AD" <%= user.getRoleID().equals("AD") ? "selected" : "" %>>Admin</option>
                    <option value="NV" <%= user.getRoleID().equals("NV") ? "selected" : "" %>>Staff</option>
                </select>

                <label>Password</label>
                <input type="text" name="password" value="<%= user.getPassword() %>" required />

                <button type="submit">Edit</button>
                <a href="MainController?action=SearchUser">Back to User List</a>
            </form>
        </div>
    </body>
</html>
