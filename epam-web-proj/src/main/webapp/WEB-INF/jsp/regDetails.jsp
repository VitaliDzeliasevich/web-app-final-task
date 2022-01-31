<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<header>
<jsp:include page="default/header.jsp" />
<jsp:include page="default/footer.jsp" />
</header>
    <body>
            <c:if test="${requestScope.incorrectPhone}">
                        <p style="color:red"><c:out value="Incorrect phone number. Please follow pattern: (xx)-xxx-xx-xx"/></p>
                        </c:if>
                        <c:remove var="incorrectPhone" />
            <h1><c:out value="${login}" />, please fill in personal Info</h1>
            <form action="MyController" method="post">
            <table>
            <input type="hidden" name="command" value="detailsRegistration" />
            <tbody>
            <tr>
            <td>Name:</td>
            <td>
            <input type="text" name="name" required value="" />
            </td>
            </tr>
            <tr>
            <td>Surname:</td>
            <td>
            <input type="text" name="surname" required value="" />
            </td>
            </tr>
            <tr>
            <td>Position:</td>
            <td><select name= "position" >
                <option value = "2" > doctor</option >
                <option value = "3" > nurse</option >
                </select></td>
            </tr>
            <tr>
            <td>Phone:</td>
            <td><input type="text" name="phone" required minlength="12" placeholder="(xx)-xxx-xx-xx" /></td>
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

    <a id="footer" href ="MyController?command=GO_TO_REGISTRATION">Go back</a>
</html>