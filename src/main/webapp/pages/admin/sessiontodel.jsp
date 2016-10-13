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
<c:import url="/pages/common/head.jsp"/>
<a href="${pageContext.servletContext.contextPath}/pages/admin/select.jsp">К выбору действия</a>
<form name="Date" action="${pageContext.servletContext.contextPath}/admin/sessiontodel" method="post">
    <table cellpadding="20">
        <tr>
            <td>Начальная дата отбора: <input type="date" name="beginDate" value="${beginDate}"/></td>
            <td>Конечная дата отбора: <input type="date" name="endDate" value="${endDate}"/></td>
            <td>
                <a href="${pageContext.servletContext.contextPath}/admin/sessiontodel?sort=hall&beginDate=${beginDate}&endDate=${endDate}">По
                    залу</a></td>
            <td>
                <a href="${pageContext.servletContext.contextPath}/admin/sessiontodel?sort=price&beginDate=${beginDate}&endDate=${endDate}">По
                    прайсу</a></td>
            <td><a href="${pageContext.servletContext.contextPath}/pages/admin/select.jsp">К выбору действия</a></td>
        </tr>
    </table>
    <br>
    <input type="submit" name="Go" value="Отобрать"/>
</form>
<form name="SessionTo" action="${pageContext.servletContext.contextPath}/admin/dochoese" method="post">
    <table cellpadding="5" border="1">
        <tr>
            <th></th>
            <th>Изображение</th>
            <th>Зал</th>
            <th>Дата сеанса</th>
            <th>Время начала сеанса</th>
            <th>Время окончания сеанса</th>
            <th>Длительность сеанса</th>
            <th>Цена</th>
            <th>Наименование фильма</th>
        </tr>
        <c:forEach items="${sessionsToDel}" var="session">
            <tr>
                <td><input type="checkbox" name="selected" value="${session.id}"/></td>
                <td><img width="30" height="40"
                         src="${pageContext.servletContext.contextPath}/image?file=${session.film.smallPoster}">
                </td>
                <td>${session.hall.name}</td>
                <td style="color: darkorchid">${dtf:getDateFromLDT(session.dateOfSeance)}</td>
                <td style="color: red">${dtf:getTimeObj(session.dateOfSeance)}</td>
                <td style="color: red">${dtf:getSeanceEnd(session.dateOfSeance, session.film.durationMin)}</td>
                <td style="color: green">${dtf:durationToH(session.film.durationMin)}</td>
                <td>${session.price}</td>
                <td>${session.film.name}</td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Delete Session" name="act"/>
    <input type="submit" value="Edit Session" name="act"/>
</form>
</body>
</html>
