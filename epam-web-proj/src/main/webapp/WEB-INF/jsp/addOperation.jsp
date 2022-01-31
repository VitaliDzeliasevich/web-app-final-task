<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
<header>
        <fmt:setLocale value="${sessionScope.localization}" />
        <fmt:setBundle basename="locale" var="loc" scope="session" />

            <fmt:message bundle="${loc}" key="operationAdded" var="operationAdded" />
            <fmt:message bundle="${loc}" key="operationNotAdded" var="operationNotAdded" />
            <fmt:message bundle="${loc}" key="radical" var="radical" />
            <fmt:message bundle="${loc}" key="palliative" var="palliative" />
            <fmt:message bundle="${loc}" key="plastic" var="plastic" />
            <fmt:message bundle="${loc}" key="type" var="type" />
            <fmt:message bundle="${loc}" key="plannedDate" var="plannedDate" />
            <fmt:message bundle="${loc}" key="goBack" var="goBack" />

<jsp:include page="default/header.jsp" />
<jsp:include page="default/footer.jsp" />
<jsp:include page="default/localization.jsp" />
</header>
    <body>
            <c:if test="${not empty requestScope.created and requestScope.created eq 'true'}">
                                <p style="color: green"> <c:out value="${operationAdded}"/> </p>
                            </c:if>
                            <c:if test="${not empty requestScope.created and requestScope.created eq 'false'}">
                                <p style="color: red"> <c:out value="${operationNotAdded}"/></p>
                            </c:if>
                            <c:remove var="discharged" />
            <form action="MyController" method="post">
            <table>
            <input type="hidden" name="command" value="addNewOperation" />
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
                    <option value = "1" > <c:out value="${radical}"/></option >
                    <option value = "2" > <c:out value="${palliative}"/></option >
                    <option value = "3" > <c:out value="${plastic}"/></option >
                    </select></td>
            </tr>
            <tr>
            <td><c:out value="${plannedDate}"/>:</td>
            <td><input type="date" name="plannedDate"  value="" /></td>
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