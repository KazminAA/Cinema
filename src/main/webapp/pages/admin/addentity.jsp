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
<h1>Введите поля для ${entity}</h1>
<form name="Addentity" method="post" accept-charset="UTF-8"
      action="${pageContext.servletContext.contextPath}/admin/add${entity}">
    <c:forEach items="${fields}" var="fieldname">
        <c:choose>
            <c:when test="${fieldname eq 'filmID'}">
                <select name="filmID">
                    <c:forEach items="${films}" var="film">
                        <option value="${film.id}">${film.name}</option>
                    </c:forEach>
                </select>
            </c:when>
            <c:otherwise>
                ${fieldname}: <input type="text" name="${fieldname}"/><br>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <input name="Добавить" type="submit"/>
</form>
</body>
</html>
