<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" uri="/WEB-INF/datetimef.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 30.09.2016
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="content-type" charset="UTF-8" content="text/html">
    <title>Добавить ${entity}</title>
</head>
<body>
<c:import url="/pages/common/head.jsp"/>
<font color="red"><c:out value="${sessionScope.message}"/></font>
<c:set var="message" value="" scope="session"/>
<c:set var="url" scope="session" value="${pageContext.servletContext.contextPath}/"/>
<h1>Введите поля для ${entity}</h1>
<c:set value="${fields}" var="fields" scope="request"/>
<form name="Addentity" method="post" accept-charset="UTF-8"
      action="${pageContext.servletContext.contextPath}/admin/datacalc">
    <c:forEach items="${fields}" var="fieldname">
        <c:choose>
            <c:when test="${fieldname eq 'film'}">
                <select name="filmID">
                    <c:forEach items="${sessionScope.films}" var="film">
                        <option value="${film.id}">${film.name}</option>
                    </c:forEach>
                </select><br>
            </c:when>
            <c:when test="${fieldname eq 'hall'}">
                <select name="hallID">
                    <c:forEach items="${sessionScope.halls}" var="hall">
                        <option value="${hall.id}">${hall.name}</option>
                    </c:forEach>
                </select><br>
            </c:when>
            <c:when test="${fieldname eq 'dateOfSeance'}">
            </c:when>
            <c:otherwise>
                ${fieldname}: <input type="text" name="${fieldname}"/><br>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <p>Введите приблизительно время начала и оконания сеансов.<br>
        Начало сеансов:<input type="time" name="beginTime"/> Конец сеансов:<input type="time" name="endTime"/>
    </p>
    <input name="Add" type="submit" value="Добавить даты/у"/>
</form>
</body>
</html>
