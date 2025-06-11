<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*, javax.servlet.*, dto.User" %>
<%
    if (session == null || session.getAttribute("USER") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    User loginUser = (User) session.getAttribute("USER");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Welcome</title>
        <style>
            body {
                margin: 0;
                padding: 0;
                font-family: Arial, sans-serif;
                background-color: #ecf5fc;
                height: 100vh;
                display: flex;
                flex-direction: column;
            }

            /* HEADER full chiều rộng */
            .header {
                background-color: #3498db;
                color: white;
                padding: 20px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                flex-shrink: 0;
            }
            .header form {
                margin: 0;
            }

            .logout-btn {
                background-color: white;
                color: #3498db;
                border: none;
                padding: 10px 20px;
                font-size: 14px;
                border-radius: 5px;
                cursor: pointer;
                transition: 0.3s;
            }

            .logout-btn:hover {
                background-color: #2980b9;
                color: white;
            }
            /* MAIN chia 2 cột */
            .main {
                flex: 1;
                display: flex;
                height: 100%;
            }

            /* MENU bên trái chiếm 1/3 */
            .menu {
                width: 15%;
                background-color: #2c80b4;
                display: flex;
                flex-direction: column;
                justify-content: center; /* giữa dọc */
                align-items: center;
                gap: 20px;
                padding: 20px;
                color: white;
            }

            /* Form trong menu */
            .menu form {
                width: 80%;
            }

            .menu button,
            .menu input[type="submit"] {
                width: 100%;
                background-color: white;
                color: #3498db;
                border: none;
                padding: 10px;
                font-size: 16px;
                border-radius: 5px;
                cursor: pointer;
                transition: 0.3s;
            }

            .menu button:hover,
            .menu input[type="submit"]:hover {
                background-color: #2980b9;
                color: white;
            }

            /* STOCKS bên phải chiếm 2/3 */
            .stocks {
                flex: 1;
                background-color: white;
                padding: 30px;
                margin: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                overflow-y: auto;
            }


        </style>
    </head>
    <body>
        <div class="header">
            <h2>Welcome <%= loginUser.getFullName() %>!</h2>
            <form action="MainController" method="get">
                <div class="button">
                    <input type="submit" name="action" value="Logout" class="logout-btn">
                </div>
            </form>
        </div>
        <div class="main">
            <div class="menu">
                <form action="MainController" method="get">
                    <div class="button">
                        <button type="submit" name="action" value="TransactionList">View Transaction</button>
                    </div>
                </form>
                <form action="MainController" method="get">
                    <div class="button">
                        <input type="submit" name="action" value="AlertList"/>
                    </div>
                </form>
                <form action="MainController" method="get">
                    <div class="button">
                        <input type="submit" name="action" value="HistoricalPriceList"/>
                    </div>
                </form>


                <form action="MainController" method="get">
                    <input type="hidden" name="action" value="SearchUser" />
                    <button type="submit" class="button-green">User List</button>
                </form>
            </div>

            <div class="stocks">
                <jsp:include page="stockList.jsp" />
            </div>

        </div>

    </body>
</html>
