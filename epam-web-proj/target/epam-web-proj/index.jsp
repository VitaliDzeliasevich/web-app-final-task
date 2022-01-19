<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<html>
        <fmt:setLocale value="${sessionScope.localization}" />
        <fmt:setBundle basename="locale" var="loc" />
            <fmt:message bundle="${loc}" key="login" var="login" />
            <fmt:message bundle="${loc}" key="password" var="password" />
            <fmt:message bundle="${loc}" key="button.ru" var="ruButton" />
            <fmt:message bundle="${loc}" key="button.en" var="enButton" />
            <fmt:message bundle="${loc}" key="select" var="select" />
            <fmt:message bundle="${loc}" key="language" var="language" />
            <fmt:message bundle="${loc}" key="registration" var="registration" />
            <fmt:message bundle="${loc}" key="authorization" var="inputFields" />
            <fmt:message bundle="${loc}" key="invalidAuthorization" var="invalidAuthorization" />

<form action="MyController" method="get" >
    <input type="hidden" name="command" value="changeLanguage"/>
    <c:out value="${language}"/> : <div> </div>
    <select name="localization">
        <option value="en" ><c:out value="${enButton}"/> </option>
        <option value="ru" ><c:out value="${ruButton}"/> </option>
    <input type="submit" value="${select}"/>
<form/>

<form action="MyController" method="get">
<input type="hidden" name="" value="" />
</form>


<head><head/>
<body>
<h1 align="center"> Hospital System </h1>
<br/>
<c:if test="${sessionScope.invalidAuthorization}">
    <p style="color:red"><c:out value="${invalidAuthorization}"/></p>
</c:if>
<c:if test="${!sessionScope.invalidAuthorization}">
    <h2> <c:out value="${inputFields}"/></h2>
</c:if>

    <form action="MyController" method="post">
        <table>
        <input type="hidden" name="command" value="logination" />
            <tbody>
            <tr>
            <td><c:out value="${login}" />:</td>
            <td>
                    <input type="text" name="login"    value="" />
            </td>
            </tr>
            <tr>
            <td><c:out value="${password}" />:</td>
                <td>
                    <input type="password" name="password"   value="" />
                </td>
                </tr>
             <tr>
             <td colspan="2">
                    <input type="submit" value="Enter" />
             </td>
             </tr>
             </tbody>
             </table>
    </form>

            <br/>
        <a href="MyController?command=GO_TO_REGISTRATION"><c:out value="${registration}"/></a>
    </body>
</html>




