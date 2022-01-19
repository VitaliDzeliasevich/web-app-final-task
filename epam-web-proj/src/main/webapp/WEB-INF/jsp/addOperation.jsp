<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
    <body>
            <c:if test="${not empty requestScope.created and requestScope.created eq 'true'}">
                                <p style="color: green"> Operation is successfully appointed</p>
                            </c:if>
                            <c:if test="${not empty requestScope.created and requestScope.created eq 'false'}">
                                <p style="color: red"> Operation has not been appointed</p>
                            </c:if>
                            <c:remove var="discharged" />
            <form action="MyController" method="post">
            <table>
            <input type="hidden" name="command" value="addNewOperation" />
            <tbody>
            <c:if test="${empty requestScope.patientID}">
            <tr>
                <td>Patient id:</td>
                <td> <input type="text" name="patientId"  value="" /> </td>
            </tr>
            </c:if>
            <tr>
                <td>Type:</td>
                <td><select name= "type" >
                    <option value = "1" > Radical</option >
                    <option value = "2" > Palliative</option >
                    <option value = "3" > Plastic</option >
                    </select></td>
            </tr>
            <tr>
            <td>Planned Date:</td>
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
    <br>
            <a href ="MyController?command=GO_TO_MAIN">Go back</a>
</html>