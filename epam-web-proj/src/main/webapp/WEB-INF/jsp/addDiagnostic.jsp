<html>
    <body>
            <form action="MyController" method="post">
            <table>
            <input type="hidden" name="command" value="addNewDiagnostic" />
            <tbody>
            <c:if test="${empty requestScope.patientID}">
            <tr>
                <td>Patient id:</td>
                <td> <input type="text" name="patientId"  value="" /> </td>
            </tr>
            </c:if>
            <tr>
                <td>Type:</td>
                <td><select name= "type" >
                    <option value = "1" > Rentgenography</option >
                    <option value = "2" > Ultrasonography</option >
                    </select></td>
            </tr>
            <tr>
            <td>Appointment Date:</td>
            <td><input type="date" name="appointmentDate"  value="" /></td>
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