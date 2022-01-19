<html>
    <body>
            <form action="MyController" method="post">
            <table>
            <input type="hidden" name="command" value="addNewAnalysis" />
            <tbody>
            <tr>
                <td>Patient id:</td>
                <td> <input type="text" name="patientId"  value="" /> </td>
            </tr>
            <tr>
                <td>Type:</td>
                <td><select name= "type" >
                    <option value = "1" > General Blood</option >
                    <option value = "2" > Biochemical Blood</option >
                    <option value = "3" > General Urine</option >
                    <option value = "4" > Biochemical Urine</option >
                    <option value = "5" > Hemostasiogram</option >
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
</html>