package by.training.epam.controller;

import by.training.epam.entity.Diagnostic;
import by.training.epam.service.DiagnosticService;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddDiagnosticCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int diagnosticType = Integer.parseInt(request.getParameter("type"));
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        String appointmentDate = request.getParameter("appointmentDate");

        DiagnosticService diagnosticService = ServiceFactory.getInstance().getDiagnosticService();
        boolean created = false;

        try {
            created = diagnosticService.create(new Diagnostic(patientId, diagnosticType, appointmentDate));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (created) {
            request.setAttribute("created", "true");
        } else {
            request.setAttribute("created", "false");
        }
        request.getRequestDispatcher("/WEB-INF/jsp/addDiagnostic.jsp").forward(request,response);
    }
}
