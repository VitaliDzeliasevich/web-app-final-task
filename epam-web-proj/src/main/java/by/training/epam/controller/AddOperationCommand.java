package by.training.epam.controller;

import by.training.epam.entity.Operation;
import by.training.epam.service.OperationService;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddOperationCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        int operationTypeId = Integer.parseInt(request.getParameter("type"));
        String plannedDate = request.getParameter("plannedDate");

        OperationService service = ServiceFactory.getInstance().getOperationService();

        boolean created = false;
        try {
           created = service.create(new Operation(patientId, operationTypeId, plannedDate));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (created) {
            request.setAttribute("created", "true");
        } else {
            request.setAttribute("created", "false");
        }
        request.getRequestDispatcher("/WEB-INF/jsp/addOperation.jsp").forward(request,response);

    }
}
