package by.training.epam.controller.impl.gotocommand;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToInitialPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String URL = CommandName.CONTROLLER_COMMAND  + CommandName.GO_TO_INITIAL_PAGE;
        boolean blocked = Boolean.parseBoolean(request.getParameter(JSPParameter.BLOCKED));
        boolean invalidAuth = Boolean.parseBoolean(request.getParameter(JSPParameter.INVALID_AUTHORIZATION));
        if (blocked) {
            request.setAttribute(JSPParameter.BLOCKED, true);
        }
        if (invalidAuth) {
            request.setAttribute(JSPParameter.INVALID_AUTHORIZATION, true);
        }

        request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        request.getRequestDispatcher(JSPPath.INITIAL_PAGE_PATH).forward(request,response);
    }
}
