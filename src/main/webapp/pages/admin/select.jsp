<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 30.09.2016
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Select to do</title>
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/admin?select=filmprepare">Добавить фильм в базу</a>
<br>
<a href="${pageContext.servletContext.contextPath}/admin?select=sessionprepare">Добавить сеанс</a>
<br>
<a href="${pageContext.servletContext.contextPath}/admin/sessiontodel">Просмотр и удаление сессий</a>
<br>
<a href="${pageContext.servletContext.contextPath}/movie?status=1">Просмотр и удаление фильмов</a>
</body>

</html>
