<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 29.09.2016
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ссылки</title>
</head>
<body>
<table cellspacing="10">
    <tr>
        <td><a href="${pageContext.servletContext.contextPath}/">Все ближайшие сеансы</a></td>
        <c:if test="${param.status != 2}">
            <td><a href="${pageContext.servletContext.contextPath}/movie?status=1">Все фильмы по рейтингу</a></td>
            <c:if test="${param.status != 1}">
                <td><a href="${pageContext.servletContext.contextPath}/?film=${film.id}">Все сеансы на этот фильм</a>
                </td>
            </c:if>
        </c:if>
    </tr>
</table>
</body>
</html>
