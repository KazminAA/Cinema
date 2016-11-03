<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 23.09.2016
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="dtf" uri="/WEB-INF/datetimef.tld" %>
<html>
<head>
    <title>Расписание сеансов на ближайшее время.</title>
</head>
<body>
<c:set var="url" scope="session" value="${pageContext.servletContext.contextPath}/"/>
<c:import url="head.jsp"/>
<p style="font-size: large; color: darkred">${message}</p>
<hr>
<table cellpadding="30">
    <tr>
        <c:forEach items="${sessionScope.hallDTOs}" var="hallDTO">
            <td><a href="${pageContext.servletContext.contextPath}/?select=hall&hall=${hallDTO.id}">${hallDTO.name}</a>
            </td>
        </c:forEach>
        <td><a href="${pageContext.servletContext.contextPath}/">Все залы</a></td>
    </tr>
</table>
<table cellpadding="5">
    <form name="date" action="${pageContext.servletContext.contextPath}/?clearDTO=true" method="post">
        <tr>
            <td>Начальная дата:<input type="date" name="beginDate"/></td>
            <td>Конечная дата:<input type="date" name="endDate"/></td>
            <td><input type="submit" value="Выбрать"/></td>
        </tr>
    </form>
</table>
<c:forEach items="${sessionScope.dates}" var="date">
    <h1><c:out value="${dtf:getDate(date)}"/></h1>
    <table frame="hsides" width="600">
        <c:forEach items="${sessions}" var="session">
            <c:if test="${dtf:isDate(date, session)}">
                <tr>
                    <td><img width="40" height="50"
                             src="${pageContext.servletContext.contextPath}/image?file=${session.film.smallPoster}">
                    </td>
                    <td><a style="color: orange"
                           href="${pageContext.servletContext.contextPath}/selecttickets?selecteds=${session.id}">
                        <c:out value="${dtf:getTimeObj(session.dateOfSeance)}"/></a></td>
                    <td>
                        <a href="${pageContext.servletContext.contextPath}/movie?id=${session.film.id}">${session.film.name}</a>
                    </td>
                    <td><c:out value="${session.hall.name}"/></td>
                </tr>
            </c:if>
    </c:forEach>
    </table>
</c:forEach>
<hr>
<c:import url="upbottom.jsp">
    <c:param name="status" value="1"/>
</c:import>
</body>
</html>
