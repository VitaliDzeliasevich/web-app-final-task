<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<html>
        <link rel="stylesheet" href="table.css" type="text/css">

        <fmt:setLocale value="${sessionScope.localization}" />
        <fmt:setBundle basename="locale" var="loc" scope="session" />

            <fmt:message bundle="${loc}" key="select" var="select" />
            <fmt:message bundle="${loc}" key="searchPatientBySurname" var="surnameSearch" />
            <fmt:message bundle="${loc}" key="search" var="search" />
            <fmt:message bundle="${loc}" key="addNewPatient" var="addNewPatient" />
            <fmt:message bundle="${loc}" key="addNewAnalysis" var="newAnalysis" />
            <fmt:message bundle="${loc}" key="addNewDiagnostic" var="newDiagnostic" />
            <fmt:message bundle="${loc}" key="addNewOperation" var="newOperation" />
            <fmt:message bundle="${loc}" key="addNewConsultation" var="newConsultation" />
            <fmt:message bundle="${loc}" key="dischargePatient" var="dischargePatient" />




<body>
<div class="container-fluid">
<jsp:include page="default/header.jsp" />

<nav class="navbar navbar-light bg-light justify-content-between">
  <a class="navbar-brand">HOSPITAL</a>
    <form class="form-inline" action = "MyController" method ="get">
          <input type= "hidden" name="command" value="searchPatientBySurname" />
          <input type = "text" name="searchPatient" minlength="1" required placeholder= "${surnameSearch}" / >
          <input type= "submit" value = "${search}"/>
    </form>
    <form class="form-inline" action = "MyController" method ="post">
        <input type= "hidden" name="command" value="GO_TO_ADD_PATIENT_PAGE" />
        <input type= "submit" value = "${addNewPatient}"/>
    </form>
    <form class="form-inline" action = "MyController" method ="post">
         <input type= "hidden" name="command" value="GO_TO_DISCHARGING" />
         <input type= "submit" value = "${dischargePatient}"/>
    </form>
    <form class="form-inline" action = "MyController" method ="post">
          <input type= "hidden" name="command" value="GO_TO_ADD_ANALYSIS_PAGE" />
          <input type= "submit" value = "${newAnalysis}"/>
    </form>
    <form class="form-inline" action = "MyController" method ="post">
          <input type= "hidden" name="command" value="GO_TO_ADD_DIAGNOSTIC_PAGE" />
          <input type= "submit" value = "${newDiagnostic}"/>
    </form>
    <form class="form-inline" action = "MyController" method ="post">
          <input type= "hidden" name="command" value="GO_TO_ADD_OPERATION" />
          <input type= "submit" value = "${newOperation}"/>
    </form>
    <form class="form-inline" action = "MyController" method ="post">
          <input type= "hidden" name="command" value="GO_TO_CONSULTATION" />
          <input type= "submit" value = "${newConsultation}"/>
    </form>
    <jsp:include page="default/localization.jsp" />
    <a class="btn btn-primary" href ="MyController?command=LOG_OUT">LogOut</a>
</nav>
    </body> <br>
        <c:if test="${sessionScope.role eq 'admin'}">
        <form align="right" action = "MyController" method ="post">
                            <input type= "hidden" name="command" value="GO_TO_REGISTRATION" />
                            <input class="btn btn-outline-warning" type="submit" value = "Registrate new User"/>
                     </form>
             <br> <br>
        </c:if>
        <h1 align="center"> <c:out value="${sessionScope.role}"/>  , you`ve entered Hospital System </h1>

    <a  href="MyController?command=GO_TO_INDEX"><c:out value="${goBack}"/></a>
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
       <tr><th>Id</th><th>Surname</th><th>Name</th><th>BirthDate</th><th>Department</th><th>Room</th><th></th></tr>
               <c:forEach var="patient" items="${searchedPatients}">
                    <form action = "MyController" method ="get">
               <input type= "hidden" name="command" value="GO_TO_PATIENT_PAGE" />
               <input type= "hidden" name="patientId" value="${patient.id}" />
               <tr><td>${patient.id}</td><td>${patient.surname}</td><td>${patient.name}</td><td>${patient.birthDate}</td>
               <td>${patient.department}</td><td>${patient.roomNumber}</td>
               <td> <input class="btn btn-secondary" type="submit" value = "Open"/></td></tr>

                    </form>
               </c:forEach>
               </table>
       </c:if>
    </div>
</html>