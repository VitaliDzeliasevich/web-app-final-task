<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<h1 align="center"> Patient ID:<c:out value="${patient.id}"/></h1>
<h3 align="left"> Name: <c:out value="${patient.name}"/> <br>
                  Surname: <c:out value="${patient.surname}"/> <br>
                  Birth Date: <c:out value="${patient.birthDate}"/> <br>
                  Department: <c:out value="${patient.department}"/> <br>
                  Room: <c:out value="${patient.roomNumber}"/> <br>
</h3>


<form action = "MyController" method ="post" align="right">
             <input type= "hidden" name="command" value="GO_TO_DISCHARGING" />
             <input type= "hidden" name="patientID" value="${patient.id}" />
             <input type= "hidden" name="trigger" value="trigger" />
             <input type= "submit" value = "{dischargePatient}"/>
         </form>

    <form action = "MyController" method ="post" align="right">
               <input type= "hidden" name="command" value="GO_TO_ADD_ANALYSIS_PAGE" />
               <input type= "hidden" name="patientID" value="${patient.id}" />
               <input type= "submit" value = "{newAnalysis}"/>
          </form>

    <form action = "MyController" method ="post" align="right">
                 <input type= "hidden" name="command" value="GO_TO_ADD_DIAGNOSTIC_PAGE" />
                 <input type= "hidden" name="patientID" value="${patient.id}" />
                 <input type= "submit" value = "{newDiagnostic}"/>
           </form>

    <form action = "MyController" method ="post" align="right">
                    <input type= "hidden" name="command" value="GO_TO_ADD_OPERATION" />
                    <input type= "hidden" name="patientID" value="${patient.id}" />
                    <input type= "submit" value = "{newOperation}"/>
           </form>

    <form action = "MyController" method ="post" align="right">
                    <input type= "hidden" name="command" value="GO_TO_CONSULTATION" />
                    <input type= "hidden" name="patientID" value="${patient.id}" />
                    <input type= "submit" value = "{newConsultation}"/>
             </form>


<a href ="MyController?command=GO_TO_MAIN">Go to main page</a>
</html>