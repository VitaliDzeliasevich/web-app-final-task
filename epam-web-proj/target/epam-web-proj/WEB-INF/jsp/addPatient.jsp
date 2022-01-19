<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <body>
            <form action="MyController" method="post">
            <table>
            <input type="hidden" name="command" value="addPatient" />
            <tbody>
            <tr>
                <td>Name:</td>
                <td> <input type="text" name="name"  value="" /> </td>
            </tr>
            <tr>
                <td>Surname:</td>
                <td><input type="text" name="surname"  value="" /></td>
            </tr>
            <tr>
                <td>BirthDate:</td>
                <td><input type="date" name="birthdate"  value="" /></td>
            </tr>
            <tr>
                <td>Sex:</td>
                <td><select name= "sex" >
                    <option value = "m" > man</option >
                    <option value = "w" > woman</option >
                    </select></td>
            </tr>
            <tr>
            <td>Department:</td>
            <td><select name= "department" >
                <option value = "Reanimation-1" > Reanimation-1</option >
                <option value = "Reanimation-2" > Reanimation-2</option >
                </select></td>
            </tr>
            <tr>
                 <td>Room:</td>
                 <td><select name= "room" >
                     <option value = "1" > 101</option >
                     <option value = "2" >102</option >
                     </select></td>
            </tr>
            <tr>
            <td>Admission Date:</td>
            <td><input type="date" name="admissionDate"  value="" /></td>
            </tr>
            <tr>
            <td>Admission Diagnosis:</td>
            <td><input type="text" name="admissionDiagnosis" required  value="" /></td>
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