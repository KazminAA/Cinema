<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 26.09.2016
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="h" uri="/WEB-INF/datetimef.tld" %>
<html>
<head>
    <title>${film.name}</title>
</head>
<body>
<h1>${film.name}</h1>
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
</body>
</html>
