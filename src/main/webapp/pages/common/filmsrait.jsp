<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 26.09.2016
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="h" uri="/WEB-INF/datetimef.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Фильмы по рейтингу</title>
</head>
<body>
<c:set var="url" value="${pageContext.servletContext.contextPath}/movie?status=1" scope="session"/>
<table cellspacing="10">
    <tr>
        <th></th>
        <th>Наименование</th>
        <th>Рейтинг</th>
        <th>Жанр</th>
        <th>Длительность</th>
        <th>Режисер</th>
    </tr>
    <c:forEach items="${films}" var="film">
        <tr>
            <td><img width="40" height="50"
                     src="${pageContext.servletContext.contextPath}/image?file=${film.smallPoster}">
            </td>
            <td><a href="${pageContext.servletContext.contextPath}/movie?id=${film.id}"> ${film.name} </a></td>
            <td style="color: brown; size: 12px">${film.raiting}</td>
            <td>${film.genre}</td>
            <td>${h:durationToH(film.durationMin)}</td>
            <td>${film.produser}</td>
        </tr>
    </c:forEach>
</table>
<hr>
<c:import url="upbottom.jsp">
    <c:param name="status" value="2"/>
</c:import>
</body>
</html>
