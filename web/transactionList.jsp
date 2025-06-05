<%-- 
    Document   : transactionList
    Created on : Jun 5, 2025, 11:23:26 AM
    Author     : User
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dto.Transaction" %>
<%@page import="dto.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction List Page</title>
    </head>
    <body>
        <% 
            User LoginUser =(User) session.getAtribute("LOGIN_USER");
            if(loginUser==null){
                response.sendRedirect("login.jsp");
            }
        %>
        <h1>Welcome: </h1>
        <form action="MainController" method="POST">
            <button type="submit" name="action" value="Logout">Logout</button>
            <input type="text" name="search" placeholder="Search"/>
            <button type="submit" name="action" value="SearchTransaction">Search</button>
        </form>
        <a href="createTransaction.jsp">Create New Transaction</a><br/>
        <a href="stockList.jsp">Go to Stock List</a><br/>
        <a href="alertList,jsp">Go to Alert List<a/><br/>
    </body>
    <!-- message -->
    <% 
        String MSG=(String) request.getAttribute("MSG");
        if(MSG!=null){
    %>
    <h3><%= MSG%></h3>   
    <%
        }
        ArrayList<TransactionDTO> list=(ArrayList<TransactionDTO>) request.getAttribute("list");
        if(list!=null){
    %>
    <table> 
        <tr>
            <th>No</th>
            <th>User ID</th>
            <th>Ticker</th> 
            <th>Type</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Status</th>
                <% if(loginUser!=null && "AD".equals(LoginUser.getRoleID())){ %>
            <th>Function</th>
                <% } %>
        </tr>
        <%
            int count=0;
            for(Transaction transaction:list){
                count++;
        %>
        <tr>
        <form action="MainController" method="POST">
            <td><%= count%></td>
            <td><%= transaction.getUserID()%></td>
            <td><%= transaction.getTicker()%></td>
            <td><%= transaction.getType()%></td>
            <td><%= transaction.getQuantity()%></td>
            <td><%= transaction.getPrice()%></td>
            <td><%= transaction.getStatus()%></td>
            <input type="hidden" name="id" value="<%= transaction.getId()%>">
            <td>
                <a href="updateTransaction.jsp">Update</a>
                <a href="deleteTransaction.jsp">Delete</a>
            </td>
        </form>
    </tr>
    <%
        }
    %>

    <%
        }
    %>

</html>
