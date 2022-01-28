package by.training.epam.controller.impl;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.JSPPath;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.entity.Operation;
import by.training.epam.service.impl.OperationService;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddOperationCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int patientId = Integer.parseInt(request.getParameter(JSPParameter.PATIENT_ID));
        int operationTypeId = Integer.parseInt(request.getParameter(JSPParameter.TYPE));
        String plannedDate = request.getParameter(JSPParameter.PLANNED_DATE);

        OperationService service = ServiceFactory.getInstance().getOperationService();

        boolean created = false;
        try {
           created = service.create(new Operation(patientId, operationTypeId, plannedDate));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (created) {
            request.setAttribute(JSPParameter.CREATED, "true");
        } else {
            request.setAttribute(JSPParameter.CREATED, "false");
        }
        request.getRequestDispatcher(JSPPath.ADD_OPERATION_PAGE_PATH).forward(request,response);

    }
}
