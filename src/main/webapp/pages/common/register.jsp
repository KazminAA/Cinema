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
    <title>Регистрация</title>
</head>
<body>
<c:set var="url" scope="session" value="${pageContext.servletContext.contextPath}/pages/common/register.jsp"/>
<h1>Введите поля для ${entity}</h1>
<h3 style="color: red"><c:out value="${sessionScope.message}"/></h3>
<form name="Addentity" method="post" accept-charset="UTF-8"
      action="${pageContext.servletContext.contextPath}/register">
    Логин: <input type="text" name="login"/><br>
    Пароль: <input type="text" name="pwd"/><br>
    Почтовый адресс: <input type="text" name="email"/><br>
    Имя: <input type="text" name="userName"/><br>
    Фамилия: <input type="text" name="userSurname"/><br>
    <input type="radio" name="sex" value="false" checked>M</input>
    <input type="radio" name="sex" value="true" checked>Ж</input><br>
    День рождения:<input type="date" name="birthday"/>
    <input name="Добавить" type="submit"/>
</form>
</body>
</html>
