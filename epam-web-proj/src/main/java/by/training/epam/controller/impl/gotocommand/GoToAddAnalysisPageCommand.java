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

public class GoToAddAnalysisPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String patientID = request.getParameter(JSPParameter.PATIENT_ID);
        if (patientID != null) {
            request.setAttribute(JSPParameter.PATIENT_ID,patientID);
            String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ADD_ANALYSIS_PAGE + "&" +
                    JSPParameter.PATIENT_ID + "=" +patientID;
            request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        } else {
            String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ADD_ANALYSIS_PAGE;
            request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.ADD_ANALYSIS_PAGE_PATH);
        dispatcher.forward(request,response);
    }
}
