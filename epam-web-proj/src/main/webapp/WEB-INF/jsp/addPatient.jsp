<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
<header>
        <fmt:setLocale value="${sessionScope.localization}" />
        <fmt:setBundle basename="locale" var="loc" scope="session" />


            <fmt:message bundle="${loc}" key="name" var="name" />
            <fmt:message bundle="${loc}" key="surname" var="surname" />
            <fmt:message bundle="${loc}" key="birthDate" var="birthDate" />
            <fmt:message bundle="${loc}" key="sex" var="sex" />
            <fmt:message bundle="${loc}" key="department" var="department" />
            <fmt:message bundle="${loc}" key="room" var="room" />
            <fmt:message bundle="${loc}" key="admissionDate" var="admissionDate" />
            <fmt:message bundle="${loc}" key="admissionDiagnosis" var="admissionDiagnosis" />
            <fmt:message bundle="${loc}" key="goBack" var="goBack" />

<jsp:include page="default/header.jsp" />
<jsp:include page="default/footer.jsp" />
<jsp:include page="default/localization.jsp" />
</header>
    <body>
            <form action="MyController" method="post">
            <table>
            <input type="hidden" name="command" value="addPatient" />
            <tbody>
            <tr><td><c:out value="${name}"/>:</td>
            <td> <input type="text" name="name"  value="" /> </td>
            </tr><tr>
                <td><c:out value="${surname}"/>:</td>
                <td><input type="text" name="surname"  value="" /></td>
            </tr><tr>
                <td><c:out value="${birthDate}"/>:</td>
                <td><input type="date" name="birthdate"  value="" /></td>
            </tr><tr>
                <td><c:out value="${sex}"/>:</td>
                <td><select name= "sex" >
                    <option value = "m" > man</option >
                    <option value = "w" > woman</option >
                    </select></td>
            </tr><tr>
            <td><c:out value="${department}"/>:</td>
            <td><select name= "department" >
                <option value = "Reanimation-1" > Reanimation-1</option >
                <option value = "Reanimation-2" > Reanimation-2</option >
                </select></td>
            </tr><tr>
                 <td><c:out value="${room}"/>:</td>
                 <td><select name= "room" >
                     <option value = "1" >101</option >
                     <option value = "2" >102</option >
                     </select></td>
            </tr><tr>
            <td><c:out value="${admissionDate}"/>:</td>
            <td><input type="date" name="admissionDate"  value="" /></td>
            </tr><tr>
            <td><c:out value="${admissionDiagnosis}"/>:</td>
            <td><input type="text" name="admissionDiagnosis" required  value="" /></td>
            </tr><tr>
            <td colspan="2">
            <input type="submit" value="Enter" />
            </td></tr>
            </tbody>
            </table>
            </form>
    </body>
    <br>
            <a id="footer" href ="MyController?command=GO_TO_MAIN"><c:out value="${goBack}"/></a>
</html>