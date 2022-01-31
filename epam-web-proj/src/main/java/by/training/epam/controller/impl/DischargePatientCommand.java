package by.training.epam.controller.impl;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPPath;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.entity.DiseaseHistory;
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

public class DischargePatientCommand implements Command {

     private final Logger log = Logger.getLogger(DischargePatientCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int diseaseHistoryId = Integer.parseInt(request.getParameter(JSPParameter.DISEASE_HISTORY_ID));
        String epicrysis = request.getParameter(JSPParameter.EPICRYSIS);
        String dischargeDate = request.getParameter(JSPParameter.DISCHARGE_DATE);

        DiseaseHistory diseaseHistory = new DiseaseHistory();
        diseaseHistory.setDischargingDate(dischargeDate);
        diseaseHistory.setEpicrysis(epicrysis);
        diseaseHistory.setId(diseaseHistoryId);

        PatientService service = ServiceFactory.getInstance().getPatientService();
        boolean discharged = false;
        try {
            discharged = service.discharge(diseaseHistory);
        } catch (ServiceException e) {
            log.log(Level.ERROR,"Discharging error", e);
            String errorMessage = "Discharging error, please try later.";
            request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
            response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
        }

        if (discharged) {
            request.setAttribute(JSPParameter.DISCHARGED, true);
        } else {
            request.setAttribute(JSPParameter.DISCHARGED, "false");
        }
        request.getRequestDispatcher(JSPPath.DISCHARGE_PAGE_PATH).forward(request,response);
    }
}
