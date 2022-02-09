package by.training.epam.controller.impl;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;
import by.training.epam.entity.Analysis;
import by.training.epam.service.Service;
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

public class UpdateAnalysisCommand implements Command {

    private final Logger log = Logger.getLogger(ShowPatientAnalysisCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int analysisId = Integer.parseInt(request.getParameter(JSPParameter.ANALYSIS_ID));
        String executionDate = request.getParameter(JSPParameter.EXECUTION_DATE);
        String result = request.getParameter(JSPParameter.RESULT);

        AnalysisService service = ServiceFactory.getInstance().getAnalysisService();
        boolean updated = false;
        List<Analysis> analysisList = null;
        try {
           updated = service.update(new Analysis(analysisId,result,executionDate));
            analysisList = service.getByPatientId((Integer) request.getSession().getAttribute(JSPParameter.PATIENT_ID));
        } catch (ServiceException e) {
            log.log(Level.ERROR,"Updating Analysis error", e);
            String errorMessage = "Smth went wrong, please try later.";
            request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
            response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);

        }

        if (!updated) {
            request.setAttribute(JSPParameter.UPDATED, false);
            request.getRequestDispatcher(JSPPath.UPDATE_ANALYSIS_PATH).forward(request,response);
        } else {
            request.setAttribute(JSPParameter.FOUND,true);
            request.setAttribute(JSPParameter.FOUND_ANALYSIS, analysisList);
            request.getRequestDispatcher(JSPPath.PATIENT_PAGE_PATH).forward(request,response);
        }

    }
}
