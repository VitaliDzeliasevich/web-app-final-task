package by.training.epam.controller.impl.gotocommand;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToAddTreatmentCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter(JSPParameter.ADDED) != null) {
            request.setAttribute(JSPParameter.ADDED, true);
        }

        if (request.getParameter(JSPParameter.NOT_ADDED) != null) {
            request.setAttribute(JSPParameter.NOT_ADDED, true);
        }

        if (request.getParameter(JSPParameter.INVALID_DATE) != null) {
            request.setAttribute(JSPParameter.INVALID_DATE, true);
        }



        String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ADD_TREATMENT;
        request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        request.getRequestDispatcher(JSPPath.ADD_TREATMENT_PAGE_PATH).forward(request,response);
    }
}
