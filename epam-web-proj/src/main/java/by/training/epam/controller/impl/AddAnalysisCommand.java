package by.training.epam.controller.impl;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;
import by.training.epam.entity.Analysis;
import by.training.epam.service.impl.AnalysisService;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddAnalysisCommand implements Command {

    Logger log = Logger.getLogger(AddAnalysisCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int analysisType = Integer.parseInt(request.getParameter(JSPParameter.TYPE));

        int patientId = Integer.parseInt(request.getParameter(JSPParameter.PATIENT_ID));


        String appointmentDate = request.getParameter(JSPParameter.APPOINTMENT_DATE);

        AnalysisService analysisService = ServiceFactory.getInstance().getAnalysisService();


        try {
            if (analysisService.validateDate(appointmentDate)) {
                boolean created = analysisService.create(new Analysis(patientId, analysisType, appointmentDate));
                if (created) {
                    String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ADD_ANALYSIS_PAGE + "&" +
                            JSPParameter.PATIENT_ID + "=" + patientId + "&" + JSPParameter.CREATED + "=" + true;
                    request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);

                    request.setAttribute(JSPParameter.CREATED, true);
                } else {
                    String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ADD_ANALYSIS_PAGE + "&" +
                            JSPParameter.PATIENT_ID + "=" + patientId + "&" + JSPParameter.NOT_ADDED + "=" + true;
                    request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);

                    request.setAttribute(JSPParameter.NOT_ADDED, true);
                }
                request.setAttribute(JSPParameter.PATIENT_ID, patientId);
                request.getRequestDispatcher(JSPPath.ADD_ANALYSIS_PAGE_PATH).forward(request,response);
            } else {
                String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ADD_ANALYSIS_PAGE + "&" +
                        JSPParameter.PATIENT_ID + "=" + patientId + "&" + JSPParameter.INVALID_DATE + "=" + true;
                request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
                request.setAttribute(JSPParameter.INVALID_DATE, true);
                request.getRequestDispatcher(JSPPath.ADD_ANALYSIS_PAGE_PATH).forward(request,response);
            }
        } catch(ServiceException e){
                log.log(Level.ERROR, "Analysis Adding error", e);
                String errorMessage = "Analysis Adding Error, please try later.";
                request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
                response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
            }
    }
}
