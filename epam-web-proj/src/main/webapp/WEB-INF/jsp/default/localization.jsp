<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
<fmt:setLocale value="${sessionScope.localization}" scope="session"/>
        <fmt:setBundle basename="locale" var="loc" scope="session" />
            <fmt:message bundle="${loc}" key="button.ru" var="ruButton" />
            <fmt:message bundle="${loc}" key="button.en" var="enButton" />
            <fmt:message bundle="${loc}" key="select" var="select" />
            <fmt:message bundle="${loc}" key="language" var="language" />

<form action="MyController" method="get" >
    <input type="hidden" name="command" value="changeLanguage"/>
    <select name="localization">
        <option value="en" ><c:out value="${enButton}"/> </option>
        <option value="ru" ><c:out value="${ruButton}"/> </option>
    <input type="submit" value="${select}"/>
</form>

</html>