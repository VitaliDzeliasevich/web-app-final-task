<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<html>
<header>
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
            <fmt:message bundle="${loc}" key="name" var="name" />
            <fmt:message bundle="${loc}" key="surname" var="surname" />
            <fmt:message bundle="${loc}" key="role" var="role" />
            <fmt:message bundle="${loc}" key="login" var="login" />
            <fmt:message bundle="${loc}" key="phone" var="phone" />
            <fmt:message bundle="${loc}" key="birthDate" var="birthDate" />
            <fmt:message bundle="${loc}" key="department" var="department" />
            <fmt:message bundle="${loc}" key="room" var="room" />
            <fmt:message bundle="${loc}" key="noPatientsFound" var="noPatientsFound" />

</header>

<body>
<div class="container-fluid">
<jsp:include page="default/header.jsp" />

<nav class="navbar navbar-light bg-light justify-content-between">
  <form class="form-inline" action = "MyController" method ="get">
            <input type= "hidden" name="command" value="GO_TO_PERSONAL_INFO" />
            <input class="btn btn-primary" type= "submit" value = "Personal Info"/>
      </form>
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
</nav> <br>
        <c:if test="${sessionScope.role eq 'admin'}">
        <form align="right" action = "MyController" method ="post">
                       <input type= "hidden" name="command" value="GO_TO_REGISTRATION" />
                       <input class="btn btn-outline-warning" type="submit" value = "Registrate new User"/>
        </form>
        <form align="right" action = "MyController" method ="post">
                        <input type= "hidden" name="command" value="SHOW_ALL_USERS" />
                        <input class="btn btn-outline-warning" type="submit" value = "Show All Users"/>
        </form>
             <br> <br>
        </c:if>

    <a  href="MyController?command=GO_TO_INDEX"><c:out value="${goBack}"/></a>
     <c:if test="${not empty requestScope.found and requestScope.found eq 'false'}">
     <p align="center" style="color:red"><c:out value="${noPatientsFound}"/></p>
     </c:if>
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
       <tr><th>Id</th><th><c:out value="${surname}"/></th><th><c:out value="${name}"/>
       </th><th><c:out value="${birthDate}"/></th><th><c:out value="${department}"/>
       </th><th><c:out value="${room}"/></th><th></th></tr>
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

       <c:if test="${requestScope.areUsersFound}">
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
              <tr><th>Id</th><th><c:out value="${login}"/></th><th><c:out value="${name}"/>
              </th><th><c:out value="${surname}"/></th><th><c:out value="${role}"/></th><th><c:out value="${phone}"/>
              </th><th></th><th></th></tr>
                      <c:forEach var="user" items="${foundUsers}">
                      <tr><td>${user.id}</td><td>${user.login}</td><td>${user.name}</td><td>${user.surname}</td>
                      <td>${user.role}</td><td>${user.phone}</td>
                        <td> <input class="btn btn-secondary" type="submit" value = "Open"/></td>
                        <c:if test="${user.isBlocked != 1}">
                        <form action = "MyController" method ="post">
                        <input type="hidden" name="foundUsers" value = "${foundUsers}" />
                        <input type="hidden" name="command" value = "BLOCK_USER" />
                        <input type="hidden" name="userId" value = "${user.id}" />
                        <td> <input class="btn btn-secondary" type="submit" value = "Block"/></td></tr>
                           </form>
                      </c:if>
                      <c:if test="${user.isBlocked == 1}">
                           <td> <input class="btn btn-secondary" type="submit" value = "Unblock"/></td></tr>
                          </form>
                      </c:if>
                      </c:forEach>
                      </table>
       </c:if>
    </div>
</body>
</html>