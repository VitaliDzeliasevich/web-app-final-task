package by.training.epam.controller.impl;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;
import by.training.epam.entity.Diagnostic;
import by.training.epam.service.impl.DiagnosticService;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddDiagnosticCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int diagnosticType = Integer.parseInt(request.getParameter(JSPParameter.TYPE));
        int patientId = Integer.parseInt(request.getParameter(JSPParameter.PATIENT_ID));
        String appointmentDate = request.getParameter(JSPParameter.APPOINTMENT_DATE);

        DiagnosticService diagnosticService = ServiceFactory.getInstance().getDiagnosticService();
        boolean created = false;

        try {
            created = diagnosticService.create(new Diagnostic(patientId, diagnosticType, appointmentDate));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (created) {
            request.setAttribute(JSPParameter.CREATED, true);
        } else {
            request.setAttribute(JSPParameter.CREATED, "false");
        }
        request.setAttribute(JSPParameter.PATIENT_ID, patientId);
        request.getRequestDispatcher(JSPPath.ADD_DIAGNOSTIC_PAGE_PATH).forward(request,response);
    }
}
