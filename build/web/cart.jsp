<%-- 
    Document   : cart
    Created on : Sep 20, 2024, 9:53:04 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${not empty products}">
            <table border="1">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th></th>
                </tr>
                <c:forEach items="${products}" var="p">
                    <tr>
                    <form action="cart" method="post">
                        <input type="hidden" name="id" value="${p.product_id}" />
                        <input type="hidden" name="action" value="remove" />
                        <td>${p.product_id}</td>
                        <td>${p.product_name}</td>
                        <td>${p.price}</td>
                        <td>${p.stock_quantity}</td>
                        <td><button type="submit">Remove</button></td>
                    </form>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty products}">
        <p>Your cart is empty.</p>
    </c:if>
    <a href="product">Home</a>
    <div>
        Total: 
         <c:set var="total" value="0" />
        <c:forEach  items="${products}" var="p">
            <c:set var="total" value="${total + (p.stock_quantity * p.price)}"></c:set>
        </c:forEach>
        <span>${total}</span>
    </div>
</body>
</html>
