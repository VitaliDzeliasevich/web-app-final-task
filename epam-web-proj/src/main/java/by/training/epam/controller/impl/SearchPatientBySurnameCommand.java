package by.training.epam.controller.impl;
import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPPath;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.entity.Patient;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class SearchPatientBySurnameCommand implements Command {

    private final Logger log = Logger.getLogger(SearchPatientBySurnameCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String patientSurname = request.getParameter(JSPParameter.SEARCH_PATIENT);
        List<Patient> patients = null;

        if (patientSurname == null) {
            patients = (List<Patient>) request.getAttribute(JSPParameter.SEARCH_PATIENT);
            request.setAttribute(JSPParameter.FOUND, true);
        }
        try {
            patients = ServiceFactory.getInstance().getPatientService().getBySurname(patientSurname);
        } catch (ServiceException e) {
            log.log(Level.ERROR,"Patient Searching error", e);
            String errorMessage = "Smth went wrong, please try later.";
            request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
            response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
        }

        if (patients!=null) {
            request.setAttribute(JSPParameter.FOUND, true);
            request.setAttribute(JSPParameter.SEARCHED_PATIENT,patients);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.MAIN_PAGE_PATH);
        dispatcher.forward(request,response);
    }
}
