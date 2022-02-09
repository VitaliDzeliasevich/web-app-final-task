<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
<head>
            <fmt:setLocale value="${sessionScope.localization}" />
            <fmt:setBundle basename="locale" var="loc" scope="session" />

            <fmt:message bundle="${loc}" key="prolongingTreatment" var="prolongingTreatment" />
            <fmt:message bundle="${loc}" key="endDate" var="endDate" />
            <fmt:message bundle="${loc}" key="invalidDateMessage" var="invalidDateMessage" />


<jsp:include page="default/header.jsp" />
<jsp:include page="default/localization.jsp" />
</head>

<h2 align="center"><c:out value="${prolongingTreatment}" /></h2>
</br>
<c:if test="${invalidDate}" >
<h2 align="center"<p style="color:red"><c:out value="${invalidDateMessage}" /></p></h2>
</c:if>
<form action="MyController" method="post">
            <table>
            <input type="hidden" name="command" value="PROLONG_TREATMENT" />
            <input type="hidden" name="treatmentId" value="${requestScope.treatmentId}" />
            <tbody>
            <tr>
            <td><c:out value="${endDate}" />:</td>
            <td><input type="date" name="endDate"  value="" /></td>
            </tr>
            <tr>
            <td colspan="2">
            <input type="submit" value="Enter" />
            </td>
            </tr>
            </tbody>
            </table>
            </form>
</html>