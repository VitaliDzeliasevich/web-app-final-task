package by.training.epam.controller.impl;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;
import by.training.epam.entity.Analysis;
import by.training.epam.entity.Patient;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;
import by.training.epam.service.impl.AnalysisService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowPatientAnalysisCommand implements Command {

    private final Logger log = Logger.getLogger(ShowPatientAnalysisCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int patientId = Integer.parseInt(request.getParameter(JSPParameter.PATIENT_ID));

        AnalysisService service = ServiceFactory.getInstance().getAnalysisService();
        List<Analysis> analysisList = null;
        try {
            analysisList = service.getByPatientId(patientId);
        } catch (ServiceException e) {
            log.log(Level.ERROR,"GetByPatientId Analysis error", e);
            String errorMessage = "Smth went wrong, please try later.";
            request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
            response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
        }
        request.setAttribute(JSPParameter.FOUND, true);
        request.setAttribute(JSPParameter.FOUND_ANALYSIS, analysisList);
        request.getRequestDispatcher(JSPPath.PATIENT_PAGE_PATH).forward(request,response);
    }
}
