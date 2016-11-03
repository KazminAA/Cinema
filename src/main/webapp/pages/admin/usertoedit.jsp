<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 12.10.2016
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование пользователей</title>
    <meta http-equiv="content-type" content="text/html" charset="utf-8">
</head>
<body>
<c:import url="/pages/common/head.jsp"/>
<a href="${pageContext.servletContext.contextPath}/pages/admin/select.jsp">К выбору действия</a>
<form name="Edit User" action="${pageContext.servletContext.contextPath}/admin/edituser?edit=edit" method="post"
      accept-charset="utf-8">
    <table cellpadding="5" border="1">
        <tr>
            <th>id</th>
            <th>Логин</th>
            <th>Пароль</th>
            <th>Почта</th>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>День рождения</th>
            <th>Роль</th>
            <th>Пол</th>
        </tr>
        <c:forEach items="${userToEdit}" var="user">
            <tr>
                <td><input type="text" name="id" value="${user.id}" readonly/></td>
                <td><input type="text" name="login" value="${user.login}"/>
                <td><input type="text" name="pwd" value="${user.pwd}"/>
                <td><input type="email" name="email" value="${user.email}"/></td>
                <td><input type="text" name="userName" value="${user.userName}"/>
                <td><input type="text" name="userSurname" value="${user.userSurname}"/></td>
                <td><input type="date" name="birthday" value="${user.birthday}"/></td>
                <td><select name="roleID">
                    <c:forEach items="${roles}" var="role">
                        <option value="${role.id}" <c:if test="${role.id == user.role.id}">selected</c:if>>
                                ${role.name}</option>
                    </c:forEach>
                </select></td>
                <td>
                    <input type="radio" name="sex" value="false" <c:if test="${user.sex == false}">checked</c:if>>муж.
                    <input type="radio" name="sex" value="true" <c:if test="${user.sex == true}">checked</c:if>>жен.
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Edit"/>
</form>
</body>
</html>
