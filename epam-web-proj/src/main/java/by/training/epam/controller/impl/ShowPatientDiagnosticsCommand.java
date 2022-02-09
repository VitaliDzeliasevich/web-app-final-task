package by.training.epam.controller.impl;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;
import by.training.epam.entity.Diagnostic;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;
import by.training.epam.service.impl.DiagnosticService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowPatientDiagnosticsCommand implements Command {

    private final Logger log = Logger.getLogger(ShowPatientDiagnosticsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int patientId = (int) request.getSession().getAttribute(JSPParameter.PATIENT_ID);
        List<Diagnostic> list = null;

        DiagnosticService service = ServiceFactory.getInstance().getDiagnosticService();

        try {
            list = service.getByPatientId(patientId);
        } catch (ServiceException e) {
            log.log(Level.ERROR,"Getting patient Diagnostics error", e);
            String errorMessage = "Smth went wrong, please try later.";
            request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
            response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
        }
        if (list!=null && list.size()>0) {
            request.setAttribute(JSPParameter.FOUND_DIAGNOSTIC, list);
            request.setAttribute(JSPParameter.IS_DIAGNOSTIC_FOUND, true);
            String URL = CommandName.CONTROLLER_COMMAND + CommandName.SHOW_PATIENT_DIAGNOSTICS + "&" + JSPParameter.FOUND
                    + "=" + true + "&" + JSPParameter.FOUND_DIAGNOSTIC + "=" + list;
            request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        } else {
            request.setAttribute(JSPParameter.IS_DIAGNOSTIC_FOUND, JSPParameter.FALSE);
            String URL = CommandName.CONTROLLER_COMMAND + CommandName.SHOW_PATIENT_DIAGNOSTICS + "&" + JSPParameter.FOUND
                    + "=" + JSPParameter.FALSE;
            request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        }

        request.getRequestDispatcher(JSPPath.PATIENT_PAGE_PATH).forward(request,response);

    }
}
