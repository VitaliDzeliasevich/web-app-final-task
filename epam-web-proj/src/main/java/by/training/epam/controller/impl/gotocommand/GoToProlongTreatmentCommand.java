package by.training.epam.controller.impl.gotocommand;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToProlongTreatmentCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int treatmentId = Integer.parseInt(request.getParameter(JSPParameter.TREATMENT_ID));

        if (request.getParameter(JSPParameter.INVALID_DATE) != null) {
            String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_PROLONG_TREATMENT + "&" +
                    JSPParameter.INVALID_DATE + "=" + true + "&" + JSPParameter.TREATMENT_ID + "=" + treatmentId;;
            request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
            request.setAttribute(JSPParameter.INVALID_DATE, true);
            request.setAttribute(JSPParameter.TREATMENT_ID, treatmentId);
            request.getRequestDispatcher(JSPPath.PROLONG_TREATMENT_PAGE_PATH).forward(request,response);
        }

        String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_PROLONG_TREATMENT + "&" +
                JSPParameter.TREATMENT_ID + "=" + treatmentId;
        request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        request.setAttribute(JSPParameter.TREATMENT_ID, treatmentId);
        request.getRequestDispatcher(JSPPath.PROLONG_TREATMENT_PAGE_PATH).forward(request,response);
    }
}
