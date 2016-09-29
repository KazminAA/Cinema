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
    <title>${film.name}</title>
</head>
<body>
<c:set var="url" value="${pageContext.servletContext.contextPath}/movie?id=${film.id}" scope="session"/>
<h1>${film.name}</h1>
<font color="orange">Рейтинг: <c:out value="${film.raiting}"/></font>
<c:if test="${sessionScope.user != null}">
    <hr>
    <form name="Raiting" action="${pageContext.servletContext.contextPath}/setraiting">
        <input type="radio" name="raiting" value="1">1</input>
        <input type="radio" name="raiting" value="2">2</input>
        <input type="radio" name="raiting" value="3">3</input>
        <input type="radio" name="raiting" value="4">4</input>
        <input type="radio" name="raiting" value="5" checked>5</input>
        <input type="hidden" name="id" value="${film.id}"/>
        <input type="submit" name="Проголосовать" style="border: none">
    </form>
    <hr>
</c:if>
<table>
    <tr>
        <td><img width="500" height="750" src="${pageContext.servletContext.contextPath}/image?file=${film.bigPoster}">
        </td>
        <td>
            <table>
                <tr>
                    <td>Жанр:</td>
                    <td>${film.genre}</td>
                </tr>
                <tr>
                    <td>Длительность:</td>
                    <td>${h:durationToH(film.durationMin)}</td>
                </tr>
                <tr>
                    <td>Год:</td>
                    <td>${film.yearOfRelease}</td>
                </tr>
                <tr>
                    <td>Страна:</td>
                    <td>${film.country}</td>
                </tr>
                <tr>
                    <td>Режисер:</td>
                    <td>${film.produser}</td>
                </tr>
                <tr>
                    <td>В ролях:</td>
                    <td>${film.cast}</td>
                </tr>
                <tr>
                    <td></td>
                    <td>${film.description}</td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<a href="${pageContext.servletContext.contextPath}/">Все ближайшие сеансы</a>
</body>
</html>
