<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
<header>
        <fmt:setLocale value="${sessionScope.localization}" />
        <fmt:setBundle basename="locale" var="loc" scope="session" />

            <fmt:message bundle="${loc}" key="analysisAdded" var="analysisAdded" />
            <fmt:message bundle="${loc}" key="analysisNotAdded" var="analysisNotAdded" />
            <fmt:message bundle="${loc}" key="generalBlood" var="generalBlood" />
            <fmt:message bundle="${loc}" key="biochemicalBlood" var="biochemicalBlood" />
            <fmt:message bundle="${loc}" key="generalUrine" var="generalUrine" />
            <fmt:message bundle="${loc}" key="biochemicalUrine" var="biochemicalUrine" />
            <fmt:message bundle="${loc}" key="hemostasiogram" var="hemostasiogram" />
            <fmt:message bundle="${loc}" key="type" var="type" />
            <fmt:message bundle="${loc}" key="appointmentDate" var="appointmentDate" />
            <fmt:message bundle="${loc}" key="goBack" var="goBack" />

<jsp:include page="default/header.jsp" />
<jsp:include page="default/footer.jsp" />
<jsp:include page="default/localization.jsp" />
</header>
    <body>

            <c:if test="${requestScope.created}">
                                <p style="color: green"> <c:out value="${analysisAdded}"/></p>
                </c:if>
            <c:if test="${not empty requestScope.created and requestScope.created eq 'false'}">
                                <p style="color: red"> <c:out value="${analysisNotAdded}"/></p>
                 </c:if>
            <c:remove var="created" />
            <form action="MyController" method="post">
            <table>
            <input type="hidden" name="command" value="addNewAnalysis" />
            <tbody>
            <c:if test="${empty requestScope.patientId}">
            <tr>
                <td>Patient id:</td>
                <td> <input type="text" name="patientId"  value="" /> </td>
            </tr>
            </c:if>
            <c:if test="${not empty requestScope.patientId}">
                    <input type="hidden" name="patientId"  value="${requestScope.patientId}" />
            </c:if>
            <tr>
                <td><c:out value="${type}"/>:</td>
                <td><select name= "type" >
                    <option value = "1" > <c:out value="${generalBlood}"/></option >
                    <option value = "2" > <c:out value="${biochemicalBlood}"/></option >
                    <option value = "3" > <c:out value="${generalUrine}"/></option >
                    <option value = "4" > <c:out value="${biochemicalUrine}"/></option >
                    <option value = "5" > <c:out value="${hemostasiogram}"/></option >
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
                <c:if test="${empty requestScope.patientId}">
                            <a id="footer" href ="MyController?command=GO_TO_MAIN"><c:out value="${goBack}"/></a>
                </c:if>
                <c:if test="${not empty requestScope.patientId}">
                            <a id="footer" href ="MyController?command=GO_TO_PATIENT_PAGE"><c:out value="${goBack}"/></a>
                </c:if>
</html>