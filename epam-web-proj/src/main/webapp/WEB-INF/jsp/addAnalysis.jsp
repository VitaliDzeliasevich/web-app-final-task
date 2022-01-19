<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
    <body>
            <c:if test="${not empty requestScope.created and requestScope.created eq 'true'}">
                                <p style="color: green"> Analysis is successfully added</p>
                            </c:if>
                            <c:if test="${not empty requestScope.created and requestScope.created eq 'false'}">
                                <p style="color: red"> Analysis has not been added</p>
                            </c:if>
                            <c:remove var="discharged" />
            <form action="MyController" method="post">
            <table>
            <input type="hidden" name="command" value="addNewAnalysis" />
            <tbody>
            <c:if test="${empty sessionScope.patientID}">
            <tr>
                <td>Patient id:</td>
                <td> <input type="text" name="patientId"  value="" /> </td>
            </tr>
            </c:if>
            <c:if test="${not empty sessionScope.patientID}">
                        <input type="hidden" name="trigger" value="trigger" />
            </c:if>
            <tr>
                <td>Type:</td>
                <td><select name= "type" >
                    <option value = "1" > General Blood</option >
                    <option value = "2" > Biochemical Blood</option >
                    <option value = "3" > General Urine</option >
                    <option value = "4" > Biochemical Urine</option >
                    <option value = "5" > Hemostasiogram</option >
                    </select></td>
            </tr>
            <tr>
            <td>Appointment Date:</td>
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
        <a href ="MyController?command=GO_TO_MAIN">Go back</a>
</html>