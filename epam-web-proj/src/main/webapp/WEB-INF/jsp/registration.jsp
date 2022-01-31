<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<html>
<header>
<jsp:include page="default/header.jsp" />
<jsp:include page="default/footer.jsp" />
</header>
    <body>
    <br><br><br><br><br>
            <c:if test="${requestScope.loginExists}">
                <p style="color:red"><c:out value="Login ${requestScope.login} already exists."/></p>
            </c:if>
            <c:remove var="loginExists" />

            <c:if test="${requestScope.incorrectPassword}">
                 <p style="color:red"><c:out value="Incorrect login or password has been input. Password must contain at least 1 Capital letter and 1 digit"/></p>
            </c:if>
            <c:remove var="incorrectPassword" />

            <form  action="MyController" method="post">
                <table>
                    <input type="hidden" name="command" value="registration" />
                        <tbody>
                        <tr>
                        <td>Create Login:</td>
                        <td>
                        <input type="text" name="login" required value="" />
                        </td>
                        </tr>
                        <tr>
                        <td>Create Password:</td>
                        <td>
                        <input type="password" name="password" required minlength="6" value="" />
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
    </body>
    <br>
        <a id="footer" href ="MyController?command=GO_TO_MAIN">Go back</a>
</html>