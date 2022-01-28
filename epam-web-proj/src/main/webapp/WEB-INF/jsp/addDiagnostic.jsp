<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<html>

<fmt:setLocale value="${sessionScope.localization}" />
        <fmt:setBundle basename="locale" var="loc" scope="session" />

            <fmt:message bundle="${loc}" key="consultationAdded" var="consultationAdded" />
            <fmt:message bundle="${loc}" key="consultationNotAdded" var="consultationNotAdded" />
            <fmt:message bundle="${loc}" key="rentgenography" var="rentgenography" />
            <fmt:message bundle="${loc}" key="ultrasonography" var="ultrasonography" />
            <fmt:message bundle="${loc}" key="type" var="type" />
            <fmt:message bundle="${loc}" key="appointmentDate" var="appointmentDate" />
            <fmt:message bundle="${loc}" key="goBack" var="goBack" />

<jsp:include page="default/header.jsp" />

    <body>
            <form action="MyController" method="post">
            <table>
            <input type="hidden" name="command" value="addNewDiagnostic" />
            <tbody>
            <c:if test="${empty requestScope.patientId}">
            <tr>
                <td>Patient id:</td>
                <td> <input type="text" name="patientId"  value="" /> </td>
            </tr>
            </c:if>
            <tr>
                <td><c:out value="${type}"/>:</td>
                <td><select name= "type" >
                    <option value = "1" > <c:out value="${rentgenography}"/></option >
                    <option value = "2" > <c:out value="${ultrasonography}"/></option >
                    </select></td>
            </tr>
            <tr>
            <td><c:out value="${appointmentDate}"/>:</td>
            <td><input type="date" name="appointmentDate"  value="" /></td>
            </tr>
            <tr>
            <td colspan="2">
            <input type="submit" value="Enter" />
            </td>
            </tr>
            </tbody>
            </table>
            </form>
    </body>
    <br>
            <a href ="MyController?command=GO_TO_MAIN"><c:out value="${goBack}"/></a>
</html>