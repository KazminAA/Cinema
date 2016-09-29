<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 29.09.2016
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<c:out value="${sessionScope.message}"/>
<form name="Login" method="post" action="${pageContext.servletContext.contextPath}/login">
    <input type="text" name="login"/><br>
    <input type="password" name="password"/>
    <input type="submit" style="border: none" name="Login"/>
</form>
</body>
</html>
