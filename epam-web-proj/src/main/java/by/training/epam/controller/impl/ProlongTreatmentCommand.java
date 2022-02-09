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

public class ProlongTreatmentCommand implements Command {

    private final Logger log = Logger.getLogger(ProlongTreatmentCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int treatmentId = Integer.parseInt(request.getParameter(JSPParameter.TREATMENT_ID));
        String endDate = request.getParameter(JSPParameter.END_DATE);

        try {
                DrugTreatmentService service = ServiceFactory.getInstance().getDrugTreatmentService();
            if (service.validateDate(endDate)) {
                service.update(new DrugTreatment(treatmentId, endDate));

                String URL = CommandName.CONTROLLER_COMMAND + CommandName.SHOW_DRUG_THERAPY;
                request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
                response.sendRedirect(URL);
            } else {
                String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_PROLONG_TREATMENT + "&" +
                        JSPParameter.INVALID_DATE + "=" + true + "&" + JSPParameter.TREATMENT_ID + "=" + treatmentId;
                request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
                request.setAttribute(JSPParameter.INVALID_DATE, true);
                request.setAttribute(JSPParameter.TREATMENT_ID, treatmentId);
                request.getRequestDispatcher(JSPPath.PROLONG_TREATMENT_PAGE_PATH).forward(request,response);
            }
        } catch (ServiceException e) {
            log.log(Level.ERROR,"Updating Treatment error", e);
            String errorMessage = "Smth went wrong, please try later.";
            request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
            response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
        }
    }
}
