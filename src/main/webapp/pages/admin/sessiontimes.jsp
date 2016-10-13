<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dtf" uri="/WEB-INF/datetimef.tld" %>
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
    <title>Добавить время сеансов</title>
</head>
<body>
<c:import url="/pages/common/head.jsp"/>
<font color="red"><c:out value="${sessionScope.message}"/></font>
<h1>Введите даты начала и конца сеансов.</h1>
<h3 style="color: orangered">Длительность фильма: <c:out
        value="${dtf:durationToH(sessionScope.film.durationMin)}"/></h3>
Название фильма: ${sessionScope.film.name}<br>
Зал: ${sessionScope.hall.name}<br>
<form name="Addentity" method="post" accept-charset="UTF-8"
      action="${pageContext.servletContext.contextPath}/admin/addsession">
    <input name="priceChk" type="number" value="${price}"/><br>
    <input name="seansTimeCount" type="hidden" value="${seansTimeCount}"/>
    <table cellpadding="20">
        <tr>
            <td>Дата начала:<input name="beginDate" type="date"/></td>
            <td>Дата окончания:<input name="endDate" type="date"/></td>
        </tr>
        <c:forEach items="${sessTimes}" var="sessTime">
            <tr>
                <td><input name="beginTime" value="${sessTime.key}" type="time"/></td>
                <td><input name="endTime" value="${sessTime.value}" type="time"/></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" name="add" value="Добавить сеансы."/>
</form>
</body>
</html>
