package by.training.epam.controller.impl;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;
import by.training.epam.entity.DrugTreatment;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;
import by.training.epam.service.impl.DrugTreatmentService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class InterruptTreatmentCommand implements Command {

    private final Logger log = Logger.getLogger(InterruptTreatmentCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int patientId = (int) request.getSession().getAttribute(JSPParameter.PATIENT_ID);
        int treatmentId = Integer.parseInt(request.getParameter(JSPParameter.TREATMENT_ID));

        DrugTreatmentService service = ServiceFactory.getInstance().getDrugTreatmentService();
        List<DrugTreatment> list = null;

        try {
            service.interrupt(treatmentId);
            list = service.getByPatientId(patientId);
        } catch (ServiceException e) {
            log.log(Level.ERROR,"Updating drug_treatment error", e);
            String errorMessage = "Smth went wrong, please try later.";
            request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
            response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
        }
        String URL = CommandName.CONTROLLER_COMMAND + CommandName.SHOW_DRUG_THERAPY + "&" + JSPParameter.FOUND_TREATMENT
                + "=" + list;
        request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        request.setAttribute(JSPParameter.FOUND_TREATMENT, list);
        request.getRequestDispatcher(JSPPath.PATIENT_PAGE_PATH).forward(request,response);
    }
}
