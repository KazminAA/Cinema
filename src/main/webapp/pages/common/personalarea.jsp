<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dtf" uri="/WEB-INF/datetimef.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 05.10.2016
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личный кабинет</title>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
</head>
<body>
<c:set var="url" scope="session" value="${pageContext.servletContext.contextPath}/personalarea"/>
<a href="${pageContext.servletContext.contextPath}/pages/common/register.jsp">Изменить данные</a><br>
<table cellpadding="5" style="border: 0ch">
    <tr>
        <th></th>
        <th>Имя фильма</th>
        <th>Дата начала</th>
        <th>Время начала</th>
        <th>Зал</th>
        <th>Ряд</th>
        <th>Место</th>
        <th>Длительность</th>
        <th>Сумма</th>
        <th></th>
    </tr>
    <c:forEach items="${tickets}" var="ticket">
        <tr>
            <td>
                <image width="30" height="40"
                       src="${pageContext.servletContext.contextPath}/image?file=${ticket.session.film.smallPoster}"/>
            </td>
            <td>${ticket.session.film.name}</td>
            <td>${dtf:getDateFromLDT(ticket.session.dateOfSeance)}</td>
            <td>${dtf:getTime(ticket.session.dateOfSeance)}</td>
            <td>${ticket.session.hall.name}</td>
            <td>${ticket.raw}</td>
            <td>${ticket.col}</td>
            <td>${dtf:durationToH(ticket.session.film.durationMin)}</td>
            <td>${ticket.session.price}</td>
            <td><c:if test="${!ticket.purchase}">
                <form name="Purchase" action="${pageContext.servletContext.contextPath}/purchase">
                    <input type="hidden" name="ticketid" value="${ticket.id}"/>
                    <input type="submit" style="border: none" name="purch" value="Купить">
                </form>
            </c:if></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
