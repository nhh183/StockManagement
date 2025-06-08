<%-- 
    Document   : stonkList
    Created on : Jun 5, 2025, 3:49:12 PM
    Author     : NHH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="style.css"/>
<div class="stocks">
    <form action="MainController" method="POST">
        <input type="text" name="search" placeholder="Search by ticker, name or sector...">
        <button type="submit" name="action" value="search">Search</button>
    </form>
    <table border="1">
        <thead>
            <tr>
                <th>No</th>
                <th>Ticker</th>
                <th>Name</th>
                <th>Sector</th>
                <th>Price</th>
                <th>Status</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <%
                int count = 0;
            %>
            <c:forEach var="stock" items="${stocks}">
                <%count++;%>
                <tr>
                    <td><%= count%></td>
                    <td>${stock.getTicker()}</td>
                    <td>${stock.getName()}</td>
                    <td>${stock.getSector()}</td>
                    <td>${stock.getPrice()}</td>
                    <td>${stock.isStatus()?"Active":"Inactive"}</td>
                    <td><a href="MainController?action=update&ticker=${stock.getTicker()}">edit</a> /
                       <a href="#" onclick="return confirm('Are you sure you want to delete this stock?')">delete</a> 
                       <!-- MainController?action=delete&ticker=${stock.getTicker()} -->
                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
    <form action="MainController" method="POST">
        <button type="submit" name="action" value="create">Create</button>
    </form>
</div>
            
