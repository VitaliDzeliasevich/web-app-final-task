<html>
    <body>
            <form action="MyController" method="post">
                <table>
                    <input type="hidden" name="command" value="registration" />
                        <tbody>
                        <tr>
                        <td>Create Login:</td>
                        <td>
                        <input type="text" name="login" required value="" />
                        </td>
                        </tr>
                        <tr>
                        <td>Create Password:</td>
                        <td>
                        <input type="password" name="password" required minlength="6" value="" />
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
    </body>
    <br>
        <a href ="MyController?command=GO_TO_INDEX">Go back</a>
</html>