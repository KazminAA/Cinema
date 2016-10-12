<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dtf" uri="/WEB-INF/datetimef.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: lex
  Date: 12.10.16
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Выбор мест</title>
</head>
<body>
<c:import url="head.jsp"/>
<table cellpadding="10" style="font-size: large; font-weight: bold; color: green">
    <tr>
        <td>${sessionDTO.film.name}</td>
        <td>${dtf:durationToH(sessionDTO.film.durationMin)}</td>
        <td>${sessionDTO.hall.name}</td>
        <td>${dtf:getDateFromLDT(sessionDTO.dateOfSeance)}</td>
        <td>${dtf:getTimeObj(sessionDTO.dateOfSeance)}</td>
    </tr>
</table>
<table cellpadding="2">
    <form name="Tickets" method="post" action="${pageContext.servletContext.contextPath}/reservtickets">
        <c:forEach items="${freeSeats}" var="raw">
            <tr>
                <c:forEach items="${raw}" var="seat">
                    <c:choose>
                        <c:when test="${seat == null}">
                            <td></td>
                        </c:when>
                        <c:otherwise>
                            <td>${seat.raw}/${seat.col}<input type="checkbox" name="sess${sessionDTO.id}raw${seat.raw}"
                                                              value="${seat.col}"/></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </c:forEach>
        <input type="submit" value="Зарезервировать."/>
    </form>
</table>
</body>
</html>
