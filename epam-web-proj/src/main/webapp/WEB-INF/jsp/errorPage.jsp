<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
<head>
<jsp:include page="default/header.jsp" />
</head>
<h1 align="center"> Error Page </h1>
<br><br><br><br><br>

<h1 align="center"><c:out value="${sessionScope.ERROR_MESSAGE}" /></h1>

</html>