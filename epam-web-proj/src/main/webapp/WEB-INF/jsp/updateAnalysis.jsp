<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
<head>
        <fmt:setLocale value="${sessionScope.localization}" />
        <fmt:setBundle basename="locale" var="loc" scope="session" />

            <fmt:message bundle="${loc}" key="updatingAnalysis" var="updatingAnalysis" />


<jsp:include page="default/header.jsp" />
<jsp:include page="default/localization.jsp" />
</head>

<h2 align="center"><c:out value="${updatingAnalysis}"</h2>

<form action="MyController" method="post">
            <table>
            <input type="hidden" name="command" value="UPDATE_ANALYSIS_COMMAND" />
            <input type="hidden" name="analysisId" value="${requestScope.analysisId}" />
            <tbody>
            <tr>
                <td>Result:</td>
                <td><input type="text" name="result"  value="" /></td>
            </tr>
            <tr>
            <td>Execution Date:</td>
            <td><input type="date" name="executionDate"  value="" /></td>
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