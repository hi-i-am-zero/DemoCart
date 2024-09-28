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
        <a href="list.jsp">Back to Products list</a>
        <h1> Your Cart</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Cart ID</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Total Items</th>
                    <th>Sub Total</th>
                    <th>Remove</th>
                    <th>Increase quantity</th>
                    <th>Decrease quantity</th>

                </tr>
            </thead>
            <tbody>
                <!-- Lặp qua danh sách CartItem trong Cart -->
                <c:forEach var="cartItem" items="${Cart.list}">
                    <tr>
                        <td>${cartItem.cartId}</td>
                        <td>${cartItem.product_id}</td>
                        <td>${cartItem.product_name}</td>
                        <td>${cartItem.price}</td>
                        <td>${cartItem.totalItem}</td>
                        <td>${cartItem.totalItem*cartItem.price}</td>
                        <td style="text-align: center">
                            <form action="remove" method="POST">
                                <input type="hidden" name="cartID"  value="${cartItem.cartId}">
                                <input type="submit" value="✖" />
                            </form>
                        </td>
                        <!--                         increase Quantity-->
                        <td style="text-align: center">
                            <form action="ascQuantity" method="POST">
                                <input type="hidden" name="id"  value="${cartItem.product_id}">
                                <input type="submit" value="+" />
                            </form>
                        </td>

                        <!--                         decrease Quantity-->
                        <td style="text-align: center">
                            <form action="descQuantity" method="POST">
                                <input type="hidden" name="id"  value="${cartItem.product_id}">
                                <input type="submit" value="-" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
    </body>
</html>
