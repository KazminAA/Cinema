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
    <title>Расписание сеансов на ближайший час.</title>
</head>
<body>
<c:forEach items="${dates}" var="date">
    <h1><c:out value="${dtf:getDate(date)}"/></h1>
    <table frame="hsides" width="600">
        <c:forEach items="${sessions}" var="session">
            <c:if test="${dtf:isDate(date, session)}">
                <tr>
                    <td><c:out value="${dtf:getTime(session.dateOfSeance)}"/></td>
                    <td><c:out value="${session.film.name}"/></td>
                    <td><c:out value="${session.hall.name}"/></td>
                </tr>
            </c:if>
    </c:forEach>
    </table>
</c:forEach>

</body>
</html>
