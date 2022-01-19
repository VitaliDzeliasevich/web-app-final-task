package by.training.epam.controller;

import by.training.epam.entity.Consultation;
import by.training.epam.service.Service;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddConsultationCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        int consTypeId = Integer.parseInt(request.getParameter("type"));

        Service<Consultation> service = ServiceFactory.getInstance().getConsultationService();

        boolean created = false;
        try {
            created = service.create(new Consultation(patientId, consTypeId));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (created) {
            request.setAttribute("created", "true");
        } else {
            request.setAttribute("created", "false");
        }

        request.getRequestDispatcher("/WEB-INF/jsp/addConsultation.jsp").forward(request,response);

    }
}
