<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
<header>
<jsp:include page="default/header.jsp" />
<jsp:include page="default/footer.jsp" />
<jsp:include page="default/localization.jsp" />

        <fmt:setLocale value="${sessionScope.localization}" />
        <fmt:setBundle basename="locale" var="loc" scope="session" />

            <fmt:message bundle="${loc}" key="select" var="select" />
            <fmt:message bundle="${loc}" key="name" var="name" />
            <fmt:message bundle="${loc}" key="surname" var="surname" />
            <fmt:message bundle="${loc}" key="goBack" var="goBack" />
            <fmt:message bundle="${loc}" key="login" var="login" />
            <fmt:message bundle="${loc}" key="phone" var="phone" />

</header>
<body>
<c:out value="${name}" />: <c:out value="${user.name}" /> <br>
<c:out value="${surname}" />: <c:out value="${user.surname}" /> <br>
<c:out value="${login}" />: <c:out value="${user.login}" /> <br>
<c:out value="${phone}" />: <c:out value="${user.phone}" /> <br>

</body>
<a id="footer" href ="MyController?command=GO_TO_MAIN"><c:out value="${goBack}"/></a>
</html>