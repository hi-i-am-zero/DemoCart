<%-- 
    Document   : login
    Created on : Sep 27, 2024, 4:13:08 PM
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
        <form action="login" method="POST">
            Username <input type="text" name="username">
            <br/>
            Password <input type="password" name="password">
            <br/>
            <div style="color: red">${error}</div>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
