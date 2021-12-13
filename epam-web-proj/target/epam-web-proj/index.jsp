<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h1 align="center"> Hospital System </h1>
<br/>
<c:if test="${invalidAuthorization}">
    <p style="color:red">Incorrect login or password, please try again</p>
</c:if>
<c:if test="${!invalidAuthorization}">
    <h2> For authorization please input fields below </h2>
</c:if>
<form action="MyController" method="post">
<table>
    <input type="hidden" name="command" value="logination" />
        <tbody>
        <tr>
         <td>Login:</td>
         <td>
         <input type="text" name="login" required  value="" />
         </td>
         </tr>
          <tr>
          <td>Password:</td>
                <td>
                    <input type="password" name="password" required value="" />
                </td>
                </tr>
            <tr>
             <td colspan="2">
                <input type="submit" value="Enter" />
                </td>
                      </tr>
                    </tbody>
                  </table>
                </form>
            <br/>
        <a href="MyController?command=GO_TO_REGISTRATION">Registration</a>
    </body>
</html>
