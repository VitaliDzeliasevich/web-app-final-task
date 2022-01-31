package by.training.epam.controller.impl;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;
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
import java.io.Writer;

public class AddPatientCommand implements Command {

    private final Logger log = Logger.getLogger(AddPatientCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter(JSPParameter.NAME);
        String surname = request.getParameter(JSPParameter.SURNAME);
        String birthdate = request.getParameter(JSPParameter.BIRTHDATE);
        String sex = request.getParameter(JSPParameter.SEX);
        String department = request.getParameter(JSPParameter.DEPARTMENT);
        int room = Integer.parseInt(request.getParameter(JSPParameter.ROOM));
        String admissionDate = request.getParameter(JSPParameter.ADMISSION_DATE);
        String admissionDiagnosis = request.getParameter(JSPParameter.ADMISSION_DIAGNOSIS);

        Patient patient = new Patient(name, surname, birthdate, sex, department,room,admissionDate, admissionDiagnosis);

        PatientService service = ServiceFactory.getInstance().getPatientService();
        try {
            service.create(patient);
        } catch (ServiceException e) {
            log.log(Level.ERROR,"Patient Adding error", e);
            String errorMessage = "Patient Adding error, please try later.";
            request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
            response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
        }

        response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_MAIN_PAGE);


    }
}
