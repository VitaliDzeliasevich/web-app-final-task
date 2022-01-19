<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<html>
        <fmt:setLocale value="${sessionScope.localization}" />
        <fmt:setBundle basename="locale" var="loc" />
        <c:out value="${sessionScope.localization}"/>
            <fmt:message bundle="${loc}" key="language" var="language" />
            <fmt:message bundle="${loc}" key="select" var="select" />
            <fmt:message bundle="${loc}" key="searchPatientBySurname" var="surnameSearch" />
            <fmt:message bundle="${loc}" key="search" var="search" />
            <fmt:message bundle="${loc}" key="button.ru" var="ruButton" />
            <fmt:message bundle="${loc}" key="button.en" var="enButton" />
            <fmt:message bundle="${loc}" key="addNewPatient" var="addNewPatient" />
            <fmt:message bundle="${loc}" key="addNewAnalysis" var="newAnalysis" />
            <fmt:message bundle="${loc}" key="addNewDiagnostic" var="newDiagnostic" />
            <fmt:message bundle="${loc}" key="addNewOperation" var="newOperation" />
            <fmt:message bundle="${loc}" key="addNewConsultation" var="newConsultation" />
            <fmt:message bundle="${loc}" key="dischargePatient" var="dischargePatient" />

<form action="MyController" method="get" >
    <input type="hidden" name="command" value="changeLanguage"/>
    <c:out value="${language}"/>:  <div> </div>
    <select name="localization">
        <option value="en" ><c:out value="${enButton}"/> </option>
        <option value="ru" ><c:out value="${ruButton}"/> </option>
    <input type="submit" value="${select}"/>
<form/>

<body>
<h1 align="center"> <c:out value="${sessionScope.login}"/>  , you`ve entered Hospital System </h1>
</br>
    <form action = "MyController" method ="get">
        <input type= "hidden" name="command" value="searchPatientBySurname" />
        <input type = "text" name="searchPatient" placeholder= "${surnameSearch}" / >
        <input type= "submit" value = "${search}"/>
    </form>

   <c:if test="${found}">
           <c:forEach var="patient" items="${searchedPatients}">
           <p>${patient}</p>
           </c:forEach>
   </c:if>

    <form action = "MyController" method ="post">
            <input type= "hidden" name="command" value="GO_TO_ADD_PATIENT_PAGE" />
            <input type= "submit" value = "${addNewPatient}"/>
        </form>

    <form action = "MyController" method ="post">
             <input type= "hidden" name="command" value="dischargePatient" />
             <input type= "submit" value = "${dischargePatient}"/>
         </form>

    <form action = "MyController" method ="post">
               <input type= "hidden" name="command" value="GO_TO_ADD_ANALYSIS_PAGE" />
               <input type= "submit" value = "${newAnalysis}"/>
          </form>

    <form action = "MyController" method ="post">
                 <input type= "hidden" name="command" value="GO_TO_ADD_DIAGNOSTIC_PAGE" />
                 <input type= "submit" value = "${newDiagnostic}"/>
           </form>

    <form action = "MyController" method ="post">
                    <input type= "hidden" name="command" value="addNewOperation" />
                    <input type= "submit" value = "${newOperation}"/>
           </form>

    <form action = "MyController" method ="post">
                    <input type= "hidden" name="command" value="AddNewConsultation" />
                    <input type= "submit" value = "${newConsultation}"/>
             </form>
    </body>
    <br>
    <a href ="MyController?command=GO_TO_INDEX">Go back</a>
</html>