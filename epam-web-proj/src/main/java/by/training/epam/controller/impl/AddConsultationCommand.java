package by.training.epam.controller.impl;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;
import by.training.epam.entity.Consultation;
import by.training.epam.service.Service;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddConsultationCommand implements Command {

    private final Logger log = Logger.getLogger(AddConsultationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int patientId = Integer.parseInt(request.getParameter(JSPParameter.PATIENT_ID));
        int consTypeId = Integer.parseInt(request.getParameter(JSPParameter.TYPE));

        Service<Consultation> service = ServiceFactory.getInstance().getConsultationService();

        boolean created = false;
        try {
            created = service.create(new Consultation(patientId, consTypeId));
        } catch (ServiceException e) {
            log.log(Level.ERROR,"Consultation Adding error", e);
            String errorMessage = "Consultation Adding error, please try later.";
            request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
            response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
        }

        if (created) {
            request.setAttribute(JSPParameter.CREATED, true);
        } else {
            request.setAttribute(JSPParameter.CREATED, "false");
        }

        request.getRequestDispatcher(JSPPath.ADD_CONSULTATION_PAGE_PATH).forward(request,response);

    }
}
