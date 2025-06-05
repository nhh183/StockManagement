<%-- 
    Document   : stonkList
    Created on : Jun 5, 2025, 3:49:12 PM
    Author     : NHH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="stocks">
    <table>
        <thead>
            <tr>
                <th>Ticker</th>
                <th>Name</th>
                <th>Sector</th>
                <th>Price</th>
                <th>Status</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="stock" items="${stocks}">
                <tr>
                    <td>${stock.getTicker()}</td>
                    <td>${stock.getName()}</td>
                    <td>${stock.getSector()}</td>
                    <td>${stock.getPrice()}</td>
                    <td>${stock.isStatus()?"Active":"Inactive"}</td>
                    <td><a href="updateStock.jsp?ticker=${stonk.getTicker()}">edit</a>/<a href="">delete</a> </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
    
</div>
