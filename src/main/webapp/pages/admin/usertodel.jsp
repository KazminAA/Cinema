<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 26.09.2016
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="h" uri="/WEB-INF/datetimef.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Пользователи</title>
</head>
<body>
<c:import url="/pages/common/head.jsp"/>
<a href="${pageContext.servletContext.contextPath}/pages/admin/select.jsp">К выбору действия</a>
<table cellpadding="5">
    <a href="${pageContext.servletContext.contextPath}/admin/usertodel?sort=userSurname">По фамилии</a>
    <a href="${pageContext.servletContext.contextPath}/admin/usertodel?sort=sex">По полу</a>
    <a href="${pageContext.servletContext.contextPath}/admin/usertodel?sort=birthday">По ДР</a>
</table>
<form name="UserDelete" action="${pageContext.servletContext.contextPath}/admin/dochoese" method="post">
    <table cellspacing="10" border="1">
        <tr>
            <th></th>
            <th>Логин</th>
            <th>Пароль</th>
            <th>Почта</th>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>День рождения</th>
            <th>Роль</th>
            <th>Пол</th>
        </tr>
        <c:forEach items="${usersToDel}" var="userToDel">
            <tr>
                <td><input type="checkbox" name="selected" value="${userToDel.id}"/></td>
                <td>${userToDel.login}</td>
                <td>${userToDel.pwd}</td>
                <td>${userToDel.email}</td>
                <td>${userToDel.userName}</td>
                <td>${userToDel.userSurname}</td>
                <td>${h:getDate(userToDel.birthday)}</td>
                <td>${userToDel.role.name}</td>
                <td>
                    <c:choose>
                        <c:when test="${userToDel.sex == false}">
                            муж.
                        </c:when>
                        <c:otherwise>
                            жен.
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" name="act" value="Delete User"/>
    <input type="submit" name="act" value="Edit User"/>
</form>
<hr>
</body>
</html>
