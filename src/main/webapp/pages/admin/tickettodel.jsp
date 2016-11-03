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
    <title>Пользователи</title>
</head>
<body>
<c:import url="/pages/common/head.jsp"/>
<a href="${pageContext.servletContext.contextPath}/pages/admin/select.jsp">К выбору действия</a>
<a href="${pageContext.servletContext.contextPath}/admin/tickettodel?sort=session">По сеансу</a>
<form name="UserDelete" action="${pageContext.servletContext.contextPath}/admin/dochoese" method="post">
    <table cellspacing="10" border="1">
        <tr>
            <th></th>
            <th>Ряд</th>
            <th>Место</th>
            <th>Проверенно</th>
            <th>Куплено</th>
            <th>Время создания</th>
            <th>Дата сеанса</th>
            <th>Время сеанса</th>
            <th>Зал</th>
            <th>UserID</th>
        </tr>
        <c:forEach items="${ticketsToDel}" var="ticket">
            <tr>
                <td><input type="checkbox" name="selected" value="${ticket.id}"/></td>
                <td>${ticket.raw}</td>
                <td>${ticket.col}</td>
                <td>${ticket.chk}</td>
                <td>${ticket.purchase}</td>
                <td>${ticket.timecreate}</td>
                <td>${h:getDateFromLDT(ticket.session.dateOfSeance)}</td>
                <td>${h:getTime(ticket.session.dateOfSeance)}</td>
                <td>${ticket.session.hall.name}</td>
                <td>${ticket.userID}</td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" name="act" value="Delete Ticket"/>
</form>
<hr>
</body>
</html>
