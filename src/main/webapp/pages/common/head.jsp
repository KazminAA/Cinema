<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 05.10.2016
  Time: 8:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добро пожаловать!</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
</head>
<body>
<table cellpadding="10">
    <tr>
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <td>Здравствуйте <c:out value="${user.userName}"/>!</td>
            </c:when>
            <c:otherwise>
                <td><a href="${pageContext.servletContext.contextPath}/pages/common/login.jsp">Вход</a></td>
                <td><a href="${pageContext.servletContext.contextPath}/pages/common/register.jsp">Регистрация</a></td>
            </c:otherwise>
        </c:choose>
    </tr>
</table>
</body>
</html>
