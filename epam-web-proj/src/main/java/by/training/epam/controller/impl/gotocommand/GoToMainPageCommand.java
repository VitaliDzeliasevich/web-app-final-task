package by.training.epam.controller.impl.gotocommand;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToMainPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().removeAttribute(JSPParameter.PATIENT_ID);

        String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_MAIN_PAGE;
        request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        request.getRequestDispatcher(JSPPath.MAIN_PAGE_PATH).forward(request,response);
    }
}
