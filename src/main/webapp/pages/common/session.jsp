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
<c:forEach items="${sessionDTOs}" var="session">
    <c:out value="${session.dateOfSeance}"/>
</c:forEach>
</body>
</html>
