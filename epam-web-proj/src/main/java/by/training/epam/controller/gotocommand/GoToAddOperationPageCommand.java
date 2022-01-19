package by.training.epam.controller.gotocommand;

import by.training.epam.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToAddOperationPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String patientID = request.getParameter("patientID");
        if (patientID != null) {
            request.setAttribute("patientID",patientID);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/addOperation.jsp").forward(request,response);
    }
}
