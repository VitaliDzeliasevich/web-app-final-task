<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
    <body>
                <c:if test="${not empty requestScope.discharged and requestScope.discharged eq 'true'}">
                    <p style="color: green"> Patient is successfully discharged</p>
                </c:if>
                <c:if test="${not empty requestScope.discharged and requestScope.discharged eq 'false'}">
                    <p style="color: red"> Patient has not been discharged</p>
                </c:if>
                <c:remove var="discharged" />
            <form action="MyController" method="post">
                <table>
                 <c:if test="${empty requestScope.patientID}">
                    <input type="hidden" name="command" value="dischargePatient" />
                        <tbody>
                        <tr>
                        <td>DiseaseHistory ID:</td>
                        <td>
                                <input type="text" name="diseaseHistoryId"  value="" />
                        </td>
                        </tr>
                        <tr>
                 </c:if>
                        <td>Epicrysis:</td>
                        <td>
                                <input type="text" name="epicrysis"  value="" />
                        </td>
                        </tr>
                        <tr>
                        <td>Discharge Date:</td>
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
    <br>
            <a href ="MyController?command=GO_TO_MAIN">Go back</a>
</html>