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
    <title>Редактирование сессий</title>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/pages/admin/select.jsp">К выбору действия</a>
<form name="SessionTo" action="${pageContext.servletContext.contextPath}/admin/editsession?edit=edit" method="post">
    <table cellpadding="5" border="1">
        <tr>
            <th>id</th>
            <th>Зал</th>
            <th>Дата сеанса</th>
            <th>Время сеанса</th>
            <th>Длительность сеанса</th>
            <th>Цена</th>
            <th>Наименование фильма</th>
        </tr>
        <c:forEach items="${sessionsToEdit}" var="session">
            <tr>
                <td><input type="text" name="id" value="${session.id}" readonly/></td>
                <td><select name="hallID">
                    <c:forEach items="${halls}" var="hall">
                        <option
                                <c:if test="${hall.id == session.hall.id}">selected</c:if>
                                value="${hall.id}">${hall.name}</option>
                    </c:forEach>
                </select></td>
                <td style="color: darkorchid"><input type="date" name="seanceData"
                                                     value="${dtf:getDateObj(session.dateOfSeance)}"/></td>
                <td style="color: red"><input type="time" name="seanceTime"
                                              value="${dtf:getTimeObj(session.dateOfSeance)}"/></td>
                <td style="color: red"><input type="number" name="duration" value="${session.film.durationMin}"
                                              readonly/></td>
                <td><input type="number" min="10" step="0.01" name="price" value="${session.price}"/></td>
                <td><select name="filmID">
                    <c:forEach items="${films}" var="film">
                        <option
                                <c:if test="${film.id == session.film.id}">selected</c:if>
                                value="${film.id}">${film.name}</option>
                    </c:forEach>
                </select></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Edit" name="act"/>
</form>
</body>
</html>
