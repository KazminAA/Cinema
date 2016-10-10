<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dtf" uri="/WEB-INF/datetimef.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 10.10.2016
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список сессий</title>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
</head>
<body>
<form name="Date" action="${pageContext.servletContext.contextPath}/admin/sessiontodel">
    <table cellpadding="20">
        <tr>
            <td>Начальная дата отбора: <input type="date" name="bedinDate"/></td>
            <td>Конечная дата отбора: <input type="date" name="endDate"/></td>
            <td>
                <a href="${pageContext.servletContext.contextPath}/admin/sessiontodel?sortBy=hall&beginDate=${beginDate}&endDate=${endDate}">По
                    залу</a></td>
            <td>
                <a href="${pageContext.servletContext.contextPath}/admin/sessiontodel?sortBy=price&beginDate=${beginDate}&endDate=${endDate}">По
                    прайсу</a></td>
        </tr>
    </table>
    <br>
    <input type="submit" name="Go" value="Отобрать"/>
</form>
<form name="SessionTo" action="${pageContext.servletContext.contextPath}/admin/delsession">
    <table cellpadding="5" border="1">
        <tr>
            <th></th>
            <th>Изображение</th>
            <th>Зал</th>
            <th>Дата сеанса</th>
            <th>Время сеанса</th>
            <th>Длительность сеанса</th>
            <th>Цена</th>
            <th>Наименование фильма</th>
        </tr>
        <c:forEach items="${sessions}" var="session">
            <tr>
                <td><input type="checkbox" name="selected" value="${session.id}"/></td>
                <td><img width="30" height="40"
                         src="${pageContext.servletContext.contextPath}/image?file=${session.film.smallPoster}">
                </td>
                <td>${session.hall.name}</td>
                <td style="color: darkorchid">${dtf:getDateFromLDT(session.dateOfSeance)}</td>
                <td style="color: red">${dtf:getTime(session.dateOfSeance)}</td>
                <td style="color: red">${dtf:durationToH(session.film.durationMin)}</td>
                <td>${session.price}</td>
                <td>${session.film.name}</td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" name="Delete" value="Удалить"/>
</form>
</body>
</html>
