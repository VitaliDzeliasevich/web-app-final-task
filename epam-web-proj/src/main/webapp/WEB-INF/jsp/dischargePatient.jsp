<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
<header>
        <fmt:setLocale value="${sessionScope.localization}" />
        <fmt:setBundle basename="locale" var="loc" scope="session" />


            <fmt:message bundle="${loc}" key="successfullyDischarged" var="successfullyDischarged" />
            <fmt:message bundle="${loc}" key="notDischarged" var="notDischarged" />
            <fmt:message bundle="${loc}" key="epicrysis" var="epicrysis" />
            <fmt:message bundle="${loc}" key="dischargeDate" var="dischargeDate" />
            <fmt:message bundle="${loc}" key="diseaseHistoryID" var="diseaseHistoryID" />
            <fmt:message bundle="${loc}" key="goBack" var="goBack" />
<jsp:include page="default/footer.jsp" />
<jsp:include page="default/header.jsp" />
<jsp:include page="default/localization.jsp" />
</header>
    <body>
                <c:if test="${requestScope.discharged}">
                    <p style="color: green"> <c:out value="${successfullyDischarged}"/></p>
                </c:if>
                <c:if test="${not empty requestScope.discharged and requestScope.discharged eq 'false'}">
                    <p style="color: red"> <c:out value="${notDischarged}"/></p>
                </c:if>
                <c:remove var="discharged" />
            <form action="MyController" method="post">
                <table>
                 <c:if test="${empty requestScope.patientId}">
                    <input type="hidden" name="command" value="dischargePatient" />
                        <tbody>
                        <tr>
                        <td><c:out value="${diseaseHistoryID}"/>:</td>
                        <td>
                                <input type="text" name="diseaseHistoryId"  value="" />
                        </td>
                        </tr>
                        <tr>
                 </c:if>
                        <td><c:out value="${epicrysis}"/>:</td>
                        <td>
                                <input type="text" name="epicrysis"  value="" />
                        </td>
                        </tr>
                        <tr>
                        <td><c:out value="${dischargeDate}"/>:</td>
                                 <td><input type="date" name="dischargeDate"  value="" /></td>
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