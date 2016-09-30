<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 29.09.2016
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Login</title>
</head>
<body>
<c:out value="${sessionScope.message}"/>
<form name="Login" method="post" accept-charset="UTF-8" action="${pageContext.servletContext.contextPath}/login">
    <input type="text" name="login"/><br>
    <input type="password" name="password"/>
    <input type="submit" style="border: none" name="Login"/>
</form>
</body>
</html>
