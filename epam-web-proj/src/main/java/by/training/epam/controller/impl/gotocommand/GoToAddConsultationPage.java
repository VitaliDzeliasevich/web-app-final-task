package by.training.epam.controller.impl.gotocommand;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToAddConsultationPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String patientID = request.getParameter(JSPParameter.PATIENT_ID);
        if (patientID != null) {
            request.setAttribute(JSPParameter.PATIENT_ID,patientID);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.ADD_CONSULTATION_PAGE_PATH);
        dispatcher.forward(request,response);
    }
}
