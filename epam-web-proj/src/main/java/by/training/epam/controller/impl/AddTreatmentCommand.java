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

public class AddTreatmentCommand implements Command {

    private final Logger log = Logger.getLogger(AddTreatmentCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    int patientId = (int) request.getSession().getAttribute(JSPParameter.PATIENT_ID);
    int drugId = Integer.parseInt(request.getParameter(JSPParameter.DRUG_ID));
    int wayOfUsingId = Integer.parseInt(request.getParameter(JSPParameter.WAY_OF_USING));
    String startDate = request.getParameter(JSPParameter.START_DATE);
    String endDate = request.getParameter(JSPParameter.END_DATE);
    String dose = request.getParameter(JSPParameter.DOSE);
    boolean created = false;
    boolean valid = false;

        try {
            DrugTreatmentService service = ServiceFactory.getInstance().getDrugTreatmentService();
            if (service.validateDate(startDate) && service.validateDate(endDate) && service.validateDate(startDate,endDate)) {
                valid = true;
                int drId = ServiceFactory.getInstance().getUserService().getByLogin(String.valueOf(request.getSession().
                        getAttribute(JSPParameter.LOGIN))).getId();
                created = service.create(new DrugTreatment(patientId, drugId, drId, startDate, endDate, dose, wayOfUsingId));
            } else {
                request.setAttribute(JSPParameter.INVALID_DATE, true);
                request.getRequestDispatcher(JSPPath.ADD_TREATMENT_PAGE_PATH).forward(request,response);
                String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ADD_TREATMENT + "&" +
                        JSPParameter.INVALID_DATE + "=" + true;
                request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
            }
        } catch (ServiceException e) {
            log.log(Level.ERROR,"Logination error", e);
            String errorMessage = "LogIn Error, please try later.";
            request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
            response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
        }

        if (created && valid) {
            String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ADD_TREATMENT + "&" + JSPParameter.ADDED +
                    "=" + true;
            request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
            request.setAttribute(JSPParameter.ADDED, true);
            request.getRequestDispatcher(JSPPath.ADD_TREATMENT_PAGE_PATH).forward(request,response);
        } else if(!created && valid) {
            String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ADD_TREATMENT + "&" + JSPParameter.NOT_ADDED +
                "=" + true;
            request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
            request.setAttribute(JSPParameter.NOT_ADDED, true);
            request.getRequestDispatcher(JSPPath.ADD_TREATMENT_PAGE_PATH).forward(request,response);
        }

    }
}
