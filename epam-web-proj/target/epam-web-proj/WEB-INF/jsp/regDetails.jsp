 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <body>
            <h1><c:out value="${login}" />, please fill in personal Info</h1>
            <form action="MyController" method="post">
            <table>
            <input type="hidden" name="command" value="detailsRegistration" />
            <tbody>
            <tr>
            <td>Name:</td>
            <td>
            <input type="text" name="name" required value="" />
            </td>
            </tr>
            <tr>
            <td>Surname:</td>
            <td>
            <input type="text" name="surname" required value="" />
            </td>
            </tr>
            <tr>
            <td>Position:</td>
            <td><select name= "position" >
                <option value = "2" > doctor</option >
                <option value = "3" > nurse</option >
                </select></td>
            </tr>
            <tr>
            <td>Specialisation:</td>
            <td><input type="text" name="specialisation"  value="" /></td>
            </tr>
            <tr>
            <td>Phone:</td>
            <td><input type="text" name="phone" required  value="" /></td>
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