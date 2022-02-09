<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<html>

        <fmt:setLocale value="${sessionScope.localization}" scope="session"/>
        <fmt:setBundle basename="locale" var="loc" scope="session" />
            <fmt:message bundle="${loc}" key="login" var="login" />
            <fmt:message bundle="${loc}" key="password" var="password" />
            <fmt:message bundle="${loc}" key="registration" var="registration" />
            <fmt:message bundle="${loc}" key="authorization" var="inputFields" />
            <fmt:message bundle="${loc}" key="invalidAuthorization" var="invalidAuthorization" />
            <fmt:message bundle="${loc}" key="youAreBlocked" var="youAreBlocked" />
            <fmt:message bundle="${loc}" key="enter" var="enter" />



<head>

<head/>

<body>
<div class="container-fluid">
<jsp:include page="default/header.jsp" />
<jsp:include page="default/localization.jsp" />
<br>
<div align="center">
<h1> Hospital System </h1>
<br/><br/>
<c:if test="${requestScope.invalidAuthorization}">
    <p style="color:red"><c:out value="${invalidAuthorization}"/></p>
</c:if>
<c:if test="${requestScope.blocked}">
    <p style="color:red"><c:out value="${youAreBlocked}"/></p>
</c:if>
<c:if test="${!invalidAuthorization}">
    <h2> <c:out value="${inputFields}"/></h2>
</c:if>
    <center><form action="MyController" method="post">
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
                    <input type="submit" class="btn btn-primary" value="${enter}" />
             </td>
             </tr>
             </tbody>
             </table>
    </form></center>
    </div>
    </div>
    </body>
</html>