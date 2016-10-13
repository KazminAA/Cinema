<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 12.10.2016
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование фильма</title>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
</head>
<body>
<c:import url="/pages/common/head.jsp"/>
<a href="${pageContext.servletContext.contextPath}/pages/admin/select.jsp">К выбору действия</a>
<form name="Edit Film" action="${pageContext.servletContext.contextPath}/admin/editfilm?edit=edit" method="post"
      accept-charset="utf-8">
    <table cellpadding="5" border="1">
        <tr>
            <th>id</th>
            <th>Наименование</th>
            <th>Дата выхода</th>
            <th>Длительность, мин</th>
            <th>Жанр</th>
            <th>Страна</th>
            <th>Режиссер</th>
            <th>В ролях</th>
            <th>Большой постер</th>
            <th>Маленький постер</th>
        </tr>
        <c:forEach items="${filmsToEdit}" var="film">
            <tr>
                <td><input type="text" name="id" value="${film.id}" readonly/></td>
                <td><input type="text" name="name" value="${film.name}"/>
                <td><input type="number" name="year" min="1970" step="1" value="${film.yearOfRelease}"/>
                <td><input type="number" name="duration" min="30" step="1" value="${film.durationMin}"/></td>
                <td><input type="text" name="genre" value="${film.genre}"/>
                <td><input type="text" name="country" value="${film.country}"/></td>
                <td><input type="text" name="produser" value="${film.produser}"/></td>
                <td><textarea name="cast" wrap="virtual" rows="3">${film.cast}</textarea></td>
                <td><textarea name="description" wrap="virtual" rows="3">${film.description}</textarea></td>
                <td><input type="text" name="smallPoster" value="${film.smallPoster}"/></td>
                <td><input type="text" name="bigPoster" value="${film.bigPoster}"/></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Edit"/>
</form>
</body>
</html>
