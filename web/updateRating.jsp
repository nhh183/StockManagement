<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dao_business_rating.RatingDAO"%>
<%@page import="dto_business_rating.Rating"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.Duration"%>
<%@page import="dto.User"%>
<%
    User loginUser = (User) session.getAttribute("USER");
    if (loginUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String userId = request.getParameter("userID");
    if (userId == null) {
        out.println("ID người dùng không hợp lệ.");
        return;
    }

    RatingDAO ratingDAO = new RatingDAO();
    Rating rating = ratingDAO.getRatingbyUserId(userId);

    if (rating == null) {
        out.println("Đánh giá không tồn tại.");
        return;
    }

    // Kiểm tra xem đánh giá có trong vòng 24 giờ không
    boolean isEditable = true;
    LocalDateTime now = LocalDateTime.now();
    Duration duration = Duration.between(rating.getCreatedAt(), now);
    if (duration.toHours() > 24) {
        isEditable = false;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Update rating page</title>
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

            button:disabled {
                background-color: #6c757d;
                cursor: not-allowed;
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

            .error-message {
                color: #dc3545;
                text-align: center;
                margin-bottom: 15px;
            }
        </style>
    </head>
    <body>
        <div class="form-container">
            <h2>Update rating</h2>
            <% if (!isEditable) { %>
                <p class="error-message">this Rating cant be able to update cause over 24 hour.</p>
            <% } %>
            <form action="UpdateRatingController" method="POST" <%= isEditable ? "" : "onsubmit='return false;'" %>>
                <input type="hidden" name="userID" value="<%= rating.getUserID() %>" />

                <label>UserID</label>
                <input type="text" value="<%= rating.getUserID() %>" disabled />

                <label>Ticker</label>
                <input type="text" name="ticker" value="<%= rating.getTicker() %>" <%= isEditable ? "required" : "disabled" %> />

                <label>Score</label>
                <input type="number" name="score" value="<%= rating.getScore() %>" min="0" max="10" step="1" <%= isEditable ? "required" : "disabled" %> />

                <label>Note</label>
                <input type="text" name="note" value="<%= rating.getNote() %>" <%= isEditable ? "required" : "disabled" %> />

                <button type="submit" <%= isEditable ? "" : "disabled" %>>Update</button>
                <a href="MainController?action=SearchRating">Back to the Rating List</a>
            </form>
        </div>
    </body>
</html>