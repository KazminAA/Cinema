<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:import url="/pages/common/head.jsp"/>
<a href="${pageContext.servletContext.contextPath}/admin?select=filmprepare">Добавить фильм в базу</a>
<br>
<a href="${pageContext.servletContext.contextPath}/admin?select=sessionprepare">Добавить сеанс</a>
<br>
<a href="${pageContext.servletContext.contextPath}/admin/sessiontodel">Просмотр и удаление сессий</a>
<br>
<a href="${pageContext.servletContext.contextPath}/movie?status=1">Просмотр и удаление фильмов</a>
<br>
<a href="${pageContext.servletContext.contextPath}/admin/usertodel">Просмотр и удаление пользователей</a>
<br>
<a href="${pageContext.servletContext.contextPath}/admin/tickettodel">Просмотр и удаление билетов</a>
<br>
</body>

</html>
