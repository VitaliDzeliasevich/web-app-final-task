package by.training.epam.controller.impl.gotocommand;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToRegistrationPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_REGISTRATION_PAGE;
        request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.REGISTRATION_PAGE_PATH);
        dispatcher.forward(request, response);
    }
}
