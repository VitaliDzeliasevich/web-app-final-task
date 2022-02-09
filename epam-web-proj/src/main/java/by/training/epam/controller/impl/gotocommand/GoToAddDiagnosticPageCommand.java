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

public class GoToAddDiagnosticPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String patientId = request.getParameter(JSPParameter.PATIENT_ID);
        if (patientId != null) {
            request.setAttribute(JSPParameter.PATIENT_ID, patientId);
            String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ADD_DIAGNOSTIC_PAGE + "&" +
                    JSPParameter.PATIENT_ID + "=" + patientId;
            request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        } else {
            String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ADD_DIAGNOSTIC_PAGE;
            request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        }

        if (request.getParameter(JSPParameter.INVALID_DATE) != null) {
            request.setAttribute(JSPParameter.INVALID_DATE, true);
        }

        if (request.getParameter(JSPParameter.CREATED) != null)  {
            request.setAttribute(JSPParameter.CREATED, true);
        }

        if (request.getParameter(JSPParameter.NOT_ADDED) != null) {
            request.setAttribute(JSPParameter.NOT_ADDED, true);
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.ADD_DIAGNOSTIC_PAGE_PATH);
        dispatcher.forward(request,response);
    }
}
