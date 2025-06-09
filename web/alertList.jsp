<%-- 
    Document   : alertList
    Created on : Jun 8, 2025, 4:59:14 PM
    Author     : loan1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="dto.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    User loginUser = (User) session.getAttribute("USER");
    if (loginUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<c:set var="loginUser" value="${sessionScope.USER}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Alert List</title>
    <style>
        form {
            margin: 20px auto;
            width: 80%;
        }
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 0 auto;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 8px;
            text-align: center;
        }
        .btn {
            padding: 6px 10px;
            margin: 5px;
        }
        .search-box {
            text-align: center;
            margin-bottom: 20px;
        }
        select, input[type="text"] {
            padding: 5px;
        }
    </style>
</head>
<body>
   <form action="transactionList.jsp" method="get">
        <div class="button">
           <input type="submit" value="View Transaction">
        </div>
   </form>

   <form action="alertList.jsp" method="get" >
        <div class="button">
            <input type="submit" value="View Alert">
        </div>
   </form>
    <form action="userList.jsp" method="get" >
        <div class="button">
            <input type="submit" value="View User">
        </div>
   </form>s
   <form action="MainController" method="get">
        <div class="button">
            <input type="submit" name="action" value="Logout">
         </div>
   </form>
    <h2 style="text-align:center;">Investment Alerts</h2>

    <div class="search-box">
        <form action="MainController" method="get">
            <input type="text" name="search" value="${sessionScope.QUERY}" placeholder="Search by ticker">
<!--            <select name="direction">
                <option value="">All Directions</option>
                <option value="increase" ${sessionScope.DIRECTION == 'increase' ? 'selected' : ''}>Increase</option>
                <option value="decrease" ${sessionScope.DIRECTION == 'decrease' ? 'selected' : ''}>Decrease</option>
            </select>
            <select name="status">
                <option value="">All Statuses</option>
                <option value="active" ${sessionScope.STATUS == 'active' ? 'selected' : ''}>Active</option>
                <option value="inactive" ${sessionScope.STATUS == 'inactive' ? 'selected' : ''}>Inactive</option>
            </select>-->
            <input type="submit" name="action" value="SearchAlert" class="btn">
        </form>
    </div>

    <table>
        <thead>
            <tr>
                <th>No</th>
                <th>Ticker</th>
                <th>Threshold</th>
                <th>Direction</th>
                <th>Status</th>
                <th>Owner</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${empty sessionScope.alerts}">
                <tr><td colspan="7">No alerts found.</td></tr>
            </c:if>
            <c:forEach var="alert" items="${sessionScope.alerts}" varStatus="loop">
                <tr>
                    <td>${loop.count}</td>
                    <td>${alert.ticker}</td>
                    <td>${alert.threshold}</td>
                    <td>${alert.direction}</td>
                    <td>${alert.status}</td>
                    <td>${alert.userID}</td>
                    <td>
                        <c:if test="${loginUser.userID == alert.userID || loginUser.roleID == 'AD'}">
                            <c:if test="${alert.status == 'inactive'}">
                                <a href="MainController?action=UpdateAlert&alertID=${alert.alertID}">Update</a>
                                <a href="MainController?action=DeleteAlert&alertID=${alert.alertID}" 
                                   onclick="return confirm('Are you sure to delete?')">Delete</a>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <form action="MainController" method="get" style="text-align:center; margin-top:20px;">
        <input type="submit" name="action" value="CreateAlert" class="btn" />
    </form>
</body>
</html>
