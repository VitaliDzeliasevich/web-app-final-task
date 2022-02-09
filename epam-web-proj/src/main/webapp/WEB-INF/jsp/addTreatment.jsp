<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
<header>
        <fmt:setLocale value="${sessionScope.localization}" />
        <fmt:setBundle basename="locale" var="loc" scope="session" />
            <fmt:message bundle="${loc}" key="goBack" var="goBack" />
            <fmt:message bundle="${loc}" key="choose" var="choose" />
            <fmt:message bundle="${loc}" key="chooseDrugGroup" var="chooseDrugGroup" />
            <fmt:message bundle="${loc}" key="antibiotic" var="antibiotic" />
            <fmt:message bundle="${loc}" key="antihypertensive" var="antihypertensive" />
            <fmt:message bundle="${loc}" key="diuretics" var="diuretics" />
            <fmt:message bundle="${loc}" key="cardiac" var="cardiac" />
            <fmt:message bundle="${loc}" key="antiInflammatory" var="antiInflammatory" />
            <fmt:message bundle="${loc}" key="painkiller" var="painkiller" />
            <fmt:message bundle="${loc}" key="infusionSolutions" var="infusionSolutions" />
            <fmt:message bundle="${loc}" key="startDate" var="startDate" />
            <fmt:message bundle="${loc}" key="endDate" var="endDate" />
            <fmt:message bundle="${loc}" key="dose" var="dose" />
            <fmt:message bundle="${loc}" key="drug" var="drug" />
            <fmt:message bundle="${loc}" key="wayOfUsing" var="wayOfUsing" />
            <fmt:message bundle="${loc}" key="appoint" var="appoint" />
            <fmt:message bundle="${loc}" key="treatmentAdded" var="treatmentAdded" />
            <fmt:message bundle="${loc}" key="treatmentNotAdded" var="treatmentNotAdded" />
            <fmt:message bundle="${loc}" key="invalidDateMessage" var="invalidDateMessage" />


<jsp:include page="default/header.jsp" />
<jsp:include page="default/footer.jsp" />
<jsp:include page="default/localization.jsp" />
</header>

<body>

    <c:if test="${added}" ><p style="color:green"><c:out value="${treatmentAdded}" /></p></c:if>
    <c:if test="${invalidDate}" >
    <h2 align="center"<p style="color:red"><c:out value="${invalidDateMessage}" /></p></h2>
    </c:if>
    <c:if test="${notAdded}" ><p style="color:red"><c:out value="${treatmentNotAdded}" /></p></c:if>

  <form action="MyController" method="post">
  <input type="hidden" name="command" value="CHOOSE_DRUG_GROUP" />
   <p><b><c:out value="${chooseDrugGroup}"/>:</b></p>
    <input name="group" type="radio" value="1" /> <c:out value="${antibiotic}"/>
    <input name="group" type="radio" value="2" /> <c:out value="${antihypertensive}"/>
    <input name="group" type="radio" value="3" /> <c:out value="${diuretics}"/>
    <input name="group" type="radio" value="4" /> <c:out value="${cardiac}"/></br>
    <input name="group" type="radio" value="5" /> <c:out value="${antiInflammatory}"/>
    <input name="group" type="radio" value="6" /> <c:out value="${painkiller}"/>
    <input name="group" type="radio" value="7" /> <c:out value="${infusionSolutions}"/>
    <input type="submit" value="${choose}"/>
  </form>

<c:if test="${not empty requestScope.group}" >
<form action="MyController" method="post">
            <table>
            <input type="hidden" name="command" value="ADD_TREATMENT" />
            <tbody>
            <td><c:if test="${requestScope.group==1}" >
            <c:out value="${drug}"/>:</td>
                    <td><select name= "drugId" >
                    <option value = "1" > Azitromicin</option >
                    <option value = "2" > Penicillin</option >
                    <option value = "3" > Cefepim</option >
                    <option value = "4" > Levofloxacin</option >
                    </select></td>
            </c:if>
            <c:if test="${requestScope.group==2}" >
            <c:out value="${drug}"/>:</td>
                   <td><select name= "drugId" >
                    <option value = "5" > Kaptopril</option >
                    <option value = "6" > Valsartn</option >
                    <option value = "7" > Ramipril</option >
                    </select></td>
            </c:if>
            <c:if test="${requestScope.group==3}" >
            <c:out value="${drug}"/>:</td>
                    <td><select name= "drugId" >
                    <option value = "8" > Furasemid</option >
                    <option value = "9" > Spironolacton</option >
                    </select></td>
            </c:if>
            <c:if test="${requestScope.group==4}" >
            <c:out value="${drug}"/>:</td>
                    <td><select name= "drugId" >
                    <option value = "10" > Dobutamin</option >
                    <option value = "11" > Adrenaline</option >
                    <option value = "12" > Nitroglycerin</option >
                    </select></td>
            </c:if>
            <c:if test="${requestScope.group==5}" >
            <c:out value="${drug}"/>:</td>
                    <td><select name= "drugId" >
                    <option value = "13" > Diclofenac</option >
                    <option value = "14" > Paracetamol</option >
                    </select></td>
            </c:if>
            <c:if test="${requestScope.group==6}" >
            <c:out value="${drug}"/>:</td>
                       <td><select name= "drugId" >
                       <option value = "15" > Tramadol</option >
                       <option value = "16" > Ketolorac</option >
                       </select></td>
            </c:if>
            <c:if test="${requestScope.group==7}" >
            <c:out value="${drug}"/>:</td>
                      <td><select name= "drugId" >
                      <option value = "17" > NaCl 0,9%</option >
                      <option value = "18" > Glucose 5%</option >
                      </select></td>
            </c:if>
            </tr><tr>
            <td><c:out value="${startDate}"/>:</td>
            <td><input type="date" name="startDate"  value="" /> </td>
            </tr><tr>
                <td><c:out value="${endDate}"/>:</td>
                <td><input type="date" name="endDate"  value="" /></td>
            </tr><tr>
                <td><c:out value="${dose}"/>:</td>
                <td><input type="text" name="dose"  value="" /></td>
            </tr><tr>
                 <td><c:out value="${wayOfUsing}"/>:</td>
                 <td><select name= "wayOfUsing" >
                     <option value = "1" >Tablets</option >
                     <option value = "2" >Intramusculary</option >
                     <option value = "3" >Intravenously</option >
                     <option value = "4" >Subcutaneously</option >
                     <option value = "5" >By inhalation</option >
                     </select></td>
            </tr><tr>
            <td colspan="2">
            <input type="submit" value="${appoint}" />
            </td></tr>
            </tbody>
            </table></form>
</c:if>
</body>
        <a id="footer" href ="MyController?command=GO_TO_PATIENT_PAGE"><c:out value="${goBack}"/></a>
</html>