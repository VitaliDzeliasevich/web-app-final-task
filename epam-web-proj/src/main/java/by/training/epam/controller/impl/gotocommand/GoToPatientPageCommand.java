package by.training.epam.controller.impl.gotocommand;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;
import by.training.epam.entity.Patient;
import by.training.epam.service.impl.PatientService;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToPatientPageCommand implements Command {

    private final Logger log = Logger.getLogger(GoToPatientPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int patientId;
        if (request.getParameter(JSPParameter.PATIENT_ID)!=null) {
            patientId = Integer.parseInt(request.getParameter(JSPParameter.PATIENT_ID));
            request.getSession().setAttribute(JSPParameter.PATIENT_ID, patientId);
        } else {
            patientId = (int) request.getSession().getAttribute(JSPParameter.PATIENT_ID);
        }

        PatientService patientService = ServiceFactory.getInstance().getPatientService();
        Patient patient = null;
        try {
            patient = patientService.getEntityById(patientId);
        } catch (ServiceException e) {
            log.log(Level.ERROR,"Patient getting by ID error", e);
            String errorMessage = "Smth went wrong, please try later :(";
            request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
            response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
        }
        String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_PATIENT_PAGE;
        request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        request.getSession().setAttribute(JSPParameter.PATIENT, patient);
        request.getRequestDispatcher(JSPPath.PATIENT_PAGE_PATH).forward(request,response);


    }
}
