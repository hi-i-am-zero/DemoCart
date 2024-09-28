<%-- 
    Document   : list
    Created on : Sep 20, 2024, 1:24:02 PM
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
        <form action="product?action=search" method="POST">
            <input type="text" name="keyword" placeholder="search...">
            <input type="submit" value="Search">
        </form>
        <br/>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Category</th>
                <th>Add to cart</th>
            </tr>
            <c:forEach items="${listProduct}" var="p">
                <tr>
                <form action="cart" method="post">
                    <input type="hidden" name="id" value="${p.product_id}" />
                    <input type="hidden" name="action" value="add" />
                    <td name="id">${p.product_id}</td>
                    <td name="name">${p.product_name}</td>
                    <td name="price">${p.price}</td>
                    <td name="quantity">${p.stock_quantity}</td>
                    <td name="category">${p.category}</td>
                    <td style="text-align: center">
                        <form action="CartItemsListController" method="POST">
                            <input type="hidden" name="id" value="${productItem.productId}">
                            <input type="submit" value="+" />
                        </form>
                    </td>
                </form>
            </tr>
        </c:forEach>
        <a href="cart.jsp"><h2>Show Your Cart</h2> </a>
    </table>
</body>
</html>
