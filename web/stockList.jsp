<%-- 
    Document   : stonkList
    Created on : Jun 5, 2025, 3:49:12 PM
    Author     : NHH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    /* Container phần stocks */
.stocks {
    flex: 1;
    padding: 30px;
    background-color: white;
    border-radius: 8px;
    margin: 0;
    height: 100%;
    overflow-y: auto;
    box-sizing: border-box;
}

/* Form search */
.stocks form {
    margin-bottom: 20px;
    display: flex;
    gap: 10px;
    flex-wrap: wrap;
}

.stocks input[type="text"] {
    flex: 1;
    padding: 10px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.stocks button {
    padding: 10px 20px;
    background-color: #3498db;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: 0.3s;
}

.stocks button:hover {
    background-color: #2980b9;
}

/* Table style */
.stocks table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
    font-size: 14px;
}

.stocks th, .stocks td {
    border: 1px solid #ddd;
    padding: 12px 10px;
    text-align: left;
}

.stocks thead {
    background-color: #f0f0f0;
}

.stocks tbody tr:hover {
    background-color: #f9f9f9;
}

/* Links in actions */
.stocks a {
    color: #3498db;
    text-decoration: none;
    margin: 0 4px;
}

.stocks a:hover {
    text-decoration: underline;
}

</style>
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
            
