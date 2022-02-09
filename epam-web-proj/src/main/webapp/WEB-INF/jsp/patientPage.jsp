<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
<header>

<jsp:include page="default/footer.jsp" />

</header>
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
            <fmt:message bundle="${loc}" key="showAllDiagnostics" var="showAllDiagnostics" />
            <fmt:message bundle="${loc}" key="type" var="type" />
            <fmt:message bundle="${loc}" key="appointmentDate" var="appointmentDate" />
            <fmt:message bundle="${loc}" key="result" var="result" />
            <fmt:message bundle="${loc}" key="executionDate" var="executionDate" />
            <fmt:message bundle="${loc}" key="addTreatment" var="addTreatment" />
            <fmt:message bundle="${loc}" key="showTherapy" var="showTherapy" />
            <fmt:message bundle="${loc}" key="startDate" var="startDate" />
            <fmt:message bundle="${loc}" key="endDate" var="endDate" />
            <fmt:message bundle="${loc}" key="dose" var="dose" />
            <fmt:message bundle="${loc}" key="drug" var="drug" />
            <fmt:message bundle="${loc}" key="drWhoAppointed" var="drWhoAppointed" />
            <fmt:message bundle="${loc}" key="wayOfUsing" var="wayOfUsing" />
            <fmt:message bundle="${loc}" key="status" var="status" />
            <fmt:message bundle="${loc}" key="inProcess" var="inProcess" />
            <fmt:message bundle="${loc}" key="interrupted" var="interrupted" />
            <fmt:message bundle="${loc}" key="interrupt" var="interrupt" />
            <fmt:message bundle="${loc}" key="prolong" var="prolong" />
            <fmt:message bundle="${loc}" key="terminationMessage" var="terminationMessage" />
            <fmt:message bundle="${loc}" key="name" var="name" />
            <fmt:message bundle="${loc}" key="surname" var="surname" />
            <fmt:message bundle="${loc}" key="birthDate" var="birthDate" />
            <fmt:message bundle="${loc}" key="department" var="department" />
            <fmt:message bundle="${loc}" key="room" var="room" />
            <fmt:message bundle="${loc}" key="admissionDate" var="admissionDate" />
            <fmt:message bundle="${loc}" key="admissionDiagnosis" var="admissionDiagnosis" />



<nav class="navbar navbar-light bg-light justify-content-between">
    <a class="navbar-brand">Patient ID:<c:out value="${patient.id}"/></a>
                <form class="form-inline" action = "MyController" method ="post">
                             <input type= "hidden" name="command" value="GO_TO_DISCHARGING" />
                             <input type= "hidden" name="patientId" value="${patient.id}" />
                             <input type= "submit" value = "${dischargePatient}"/>
                </form>
                <form class="form-inline" action = "MyController" method ="post" align="right">
                           <input type= "hidden" name="command" value="SHOW_PATIENT_ANALYZES" />
                           <input type= "hidden" name="patientId" value="${patient.id}" />
                           <input type= "submit" value = "${showAllAnalysis}"/>
                </form>
                <form class="form-inline" action = "MyController" method ="post" align="right">
                            <input type= "hidden" name="command" value="SHOW_PATIENT_DIAGNOSTICS" />
                            <input type= "hidden" name="patientId" value="${patient.id}" />
                            <input type= "submit" value = "${showAllDiagnostics}"/>
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
<h3 align="left">   <c:out value="${name}"/>: <c:out value="${patient.name}"/> <br>
                    <c:out value="${surname}"/>: <c:out value="${patient.surname}"/> <br>
                    <c:out value="${birthDate}"/>: <c:out value="${patient.birthDate}"/> <br>
                    <c:out value="${department}"/>: <c:out value="${patient.department}"/> <br>
                    <c:out value="${room}"/>: <c:out value="${patient.roomNumber}"/> <br>
                    <c:out value="${admissionDate}"/>: <c:out value="${patient.admissionDate}"/> <br>
                    <c:out value="${admissionDiagnosis}"/>: <c:out value="${patient.admissionDiagnosis}"/> <br>
</h3>

                <form action = "MyController" method ="post">
                      <input type= "hidden" name="command" value="GO_TO_ADD_TREATMENT" />
                      <input type= "submit" value = "${addTreatment}"/>
                </form>

                <form action = "MyController" method ="get">
                      <input type= "hidden" name="command" value="SHOW_DRUG_THERAPY" />
                      <input type= "submit" value = "${showTherapy}"/>
                </form>


<c:if test="${not empty requestScope.foundTreatment}">
<style type="text/css">
       TABLE {
        border-collapse: collapse;
       }
       .scroll-table-body {
              	height: 300px;
              	overflow-x: auto;
              	margin-top: 0px;
              	margin-bottom: 20px;
              	border-bottom: 1px solid #eee;
       }
       TD, TH {
        padding: 3px;
        border: 1px solid black;
       }
       TH {
        background: #b0e0e6;
       }
      </style>
            <div class="scroll-table-body">
            <table align="center">
            <colgroup>
                <col span="5" style="background:Khaki">
            </colgroup>
            <tr><th>Id</th><th><c:out value="${drug}"/></th><th><c:out value="${drWhoAppointed}"/></th>
            <th><c:out value="${dose}"/></th><th><c:out value="${wayOfUsing}"/></th><th><c:out value="${startDate}"/></th>
            <th><c:out value="${endDate}"/></th><th><c:out value="${status}"/></th><th></th></tr>
           <c:forEach var="treatment" items="${foundTreatment}">
           <tr><td>${treatment.id}</td><td>${treatment.drugName}</td><td>${treatment.drSurname}
           </td><td>${treatment.dose}</td><td>${treatment.wayOfUsing}</td><td>${treatment.startDate}</td>
           <td>${treatment.endDate}</td>
           <td><c:if test="${treatment.isInterrupted != 1}"><c:out value="${inProcess}"/></c:if>
           <c:if test="${treatment.isInterrupted == 1}"><c:out value="${interrupted}"/></c:if></td>
           <td><c:if test="${treatment.isInterrupted != 1}">
           <form action = "MyController" method ="post">
                                    <input type= "hidden" name="command" value="INTERRUPT_TREATMENT" />
                                    <input type= "hidden" name="treatmentId" value="${treatment.id}" />
                                    <input class="btn btn-secondary"  type= "submit" value = "${interrupt}"/>
           </form>
           </c:if>
           <c:if test="${treatment.isTerminated && treatment.isInterrupted != 1}">
           <form action = "MyController" method ="post">
                                    <input type= "hidden" name="command" value="GO_TO_PROLONG_TREATMENT" />
                                    <input type= "hidden" name="treatmentId" value="${treatment.id}" />
                                    <input class="btn btn-secondary"  type= "submit" value = "${prolong}"/>
           </form>
               <p style="color:red"><c:out value="${terminationMessage}" /></p>
           </c:if></td>
           </tr>
           </c:forEach>
           </table>
           </div>
</c:if>

<c:if test="${requestScope.found}">
<style type="text/css">
       TABLE {
        border-collapse: collapse;
       }
       .scroll-table-body {
       	height: 300px;
       	overflow-x: auto;
       	margin-top: 0px;
       	margin-bottom: 20px;
       	border-bottom: 1px solid #eee;
       }
       TD, TH {
        padding: 3px;
        border: 1px solid black;
       }
       TH {
        background: #b0e0e6;
       }
      </style>
            <div class="scroll-table-body">
            <table align="center">
            <colgroup>
                <col span="5" style="background:Khaki">
            </colgroup>
            <tr><th>Id</th><th><c:out value="${type}"/></th><th><c:out value="${appointmentDate}"/></th>
            <th><c:out value="${result}"/></th><th><c:out value="${executionDate}"/></th></tr>
           <c:forEach var="analysis" items="${foundAnalysis}">
           <tr><td>${analysis.id}</td><td>${analysis.type}</td><td>${analysis.appointmentDate}
           </td><td>${analysis.result}</td><td>${analysis.executionDate}</td>
           <c:if test="${empty analysis.result}">
           <td><form action = "MyController" method ="post">
                         <input type= "hidden" name="command" value="GO_TO_UPDATE_ANALYSIS" />
                         <input type= "hidden" name="analysisId" value="${analysis.id}" />
                         <input class="btn btn-secondary"  type= "submit" value = "Update"/>
               </form></td>
           </c:if>
           </tr>
           </c:forEach>
           </table>
           </div>
   </c:if>


   <c:if test="${requestScope.isDiagnosticFound}">
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
               <tr><th>Id</th><th><c:out value="${type}"/></th><th><c:out value="${appointmentDate}"/></th>
               <th><c:out value="${result}"/></th><th><c:out value="${executionDate}"/></th></tr>
              <c:forEach var="diagnostic" items="${foundDiagnostic}">
              <tr><td>${diagnostic.id}</td><td>${diagnostic.type}</td><td>${diagnostic.appointmentDate}
              </td><td>${diagnostic.result}</td><td>${diagnostic.executionDate}</td>
              <c:if test="${empty diagnostic.result}">
              <td><input class="btn btn-secondary"  type= "submit" value = "Update"/></td>
              </c:if>
              </tr>
              </c:forEach>
              </table>
      </c:if>
</div>
<a id="footer" href ="MyController?command=GO_TO_MAIN">Go to main page</a>
</body>
</html>