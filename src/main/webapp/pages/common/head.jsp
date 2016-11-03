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
<c:import url="/pages/common/head.jsp"/>
<table cellpadding="10">
    <tr>
        <td><a href="${pageContext.servletContext.contextPath}/">На главную</a></td>
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <td>Здравствуйте <c:out value="${user.userName}"/>!</td>
                <td><a href="${pageContext.servletContext.contextPath}/exit">Выход</a></td>
                <td><a href="${pageContext.servletContext.contextPath}/personalarea">Личный кабинет</a></td>
                <c:if test="${sessionScope.user.role.name eq 'Admin'}">
                    <td><a href="${pageContext.servletContext.contextPath}/pages/admin/select.jsp">Админка</a></td>
                </c:if>
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
