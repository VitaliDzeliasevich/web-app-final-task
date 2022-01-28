<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
<link rel="stylesheet" href="table.css">
        <fmt:setLocale value="${sessionScope.localization}" />
        <fmt:setBundle basename="locale" var="loc" scope="session" />

            <fmt:message bundle="${loc}" key="select" var="select" />
            <fmt:message bundle="${loc}" key="search" var="search" />
            <fmt:message bundle="${loc}" key="addNewPatient" var="addNewPatient" />
            <fmt:message bundle="${loc}" key="addNewAnalysis" var="newAnalysis" />
            <fmt:message bundle="${loc}" key="addNewDiagnostic" var="newDiagnostic" />
            <fmt:message bundle="${loc}" key="addNewOperation" var="newOperation" />
            <fmt:message bundle="${loc}" key="addNewConsultation" var="newConsultation" />
            <fmt:message bundle="${loc}" key="dischargePatient" var="dischargePatient" />
            <fmt:message bundle="${loc}" key="showAllAnalysis" var="showAllAnalysis" />


<nav class="navbar navbar-light bg-light justify-content-between">
    <a class="navbar-brand">Patient ID:<c:out value="${patient.id}"/></a>
                <form class="form-inline" action = "MyController" method ="post">
                             <input type= "hidden" name="command" value="GO_TO_DISCHARGING" />
                             <input type= "hidden" name="patientId" value="${patient.id}" />
                             <input type= "hidden" name="trigger" value="trigger" />
                             <input type= "submit" value = "${dischargePatient}"/>
                </form>
                <form class="form-inline" action = "MyController" method ="post" align="right">
                           <input type= "hidden" name="command" value="SHOW_PATIENT_ANALYZES" />
                           <input type= "hidden" name="patientId" value="${patient.id}" />
                           <input type= "submit" value = "${showAllAnalysis}"/>
                </form>
                <form class="form-inline" action = "MyController" method ="post">
                      <input type= "hidden" name="command" value="GO_TO_ADD_ANALYSIS_PAGE" />
                      <input type= "hidden" name="patientId" value="${patient.id}" />
                      <input type= "submit" value = "${newAnalysis}"/>
                </form>
                <form class="form-inline" action = "MyController" method ="post">
                      <input type= "hidden" name="command" value="GO_TO_ADD_DIAGNOSTIC_PAGE" />
                      <input type= "hidden" name="patientId" value="${patient.id}" />
                      <input type= "submit" value = "${newDiagnostic}"/>
                </form>
                <form class="form-inline" action = "MyController" method ="post">
                      <input type= "hidden" name="command" value="GO_TO_ADD_OPERATION" />
                      <input type= "hidden" name="patientId" value="${patient.id}" />
                      <input type= "submit" value = "${newOperation}"/>
                </form>
                <form class="form-inline" action = "MyController" method ="post">
                      <input type= "hidden" name="command" value="GO_TO_CONSULTATION" />
                      <input type= "hidden" name="patientId" value="${patient.id}" />
                      <input type= "submit" value = "${newConsultation}"/>
                </form>
                <jsp:include page="default/localization.jsp" />
                <a class="btn btn-primary" href ="MyController?command=LOG_OUT">LogOut</a>
</nav>
<body>
<div class="container-fluid">
<jsp:include page="default/header.jsp" />
<h3 align="left">   Name: <c:out value="${patient.name}"/> <br>
                    Surname: <c:out value="${patient.surname}"/> <br>
                    Birth date: <c:out value="${patient.birthDate}"/> <br>
                    Department: <c:out value="${patient.department}"/> <br>
                    Room: <c:out value="${patient.roomNumber}"/> <br>
                    Admission date: <c:out value="${patient.admissionDate}"/> <br>
                    Admission diagnosis: <c:out value="${patient.admissionDiagnosis}"/> <br>
</h3>

<c:if test="${requestScope.found}">
<style type="text/css">
       TABLE {
        border-collapse: collapse;
       }
       TD, TH {
        padding: 3px;
        border: 1px solid black;
       }
       TH {
        background: #b0e0e6;
       }
      </style>
            <table align="center">
            <colgroup>
                <col span="5" style="background:Khaki">
            </colgroup>
            <tr><th>Id</th><th>Type</th><th>Appoint.Date</th><th>Result</th><th>ExecutionDate</th></tr>
           <c:forEach var="analysis" items="${foundAnalysis}">
           <tr><td>${analysis.id}</td><td>${analysis.type}</td><td>${analysis.appointmentDate}
           </td><td>${analysis.result}</td><td>${analysis.executionDate}</td>
           <c:if test="${empty analysis.result}">
           <td><form action = "MyController" method ="post">
                         <input type= "hidden" name="command" value="GO_TO_UPDATE_ANALYSIS" />
                         <input type= "hidden" name="analysisId" value="${analysis.id}" />
                         <input  type= "submit" value = "Update"/>
               </form></td>
           </c:if>
           </tr>
           </c:forEach>
           </table>
   </c:if>
</div>
</body>
<a href ="MyController?command=GO_TO_MAIN">Go to main page</a>
</html>