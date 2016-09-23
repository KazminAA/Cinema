<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 23.09.2016
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1><c:out value="${date1}"/></h1>
<p>
    <c:forEach items="${sessions1}" var="session">
        <c:out value="${session}"/><br>
</c:forEach>
</p>
<h1><c:out value="${date2}"/></h1>
<p>
    <c:forEach items="${sessions2}" var="session">
        <c:out value="${session}"/><br>
    </c:forEach>
</p>
<h1><c:out value="${date3}"/></h1>
<p>
    <c:forEach items="${sessions3}" var="session">
        <c:out value="${session}"/><br>
    </c:forEach>
</p>
</body>
</html>
