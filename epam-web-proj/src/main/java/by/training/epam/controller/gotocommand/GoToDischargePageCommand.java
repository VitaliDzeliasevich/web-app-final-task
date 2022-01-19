package by.training.epam.controller.gotocommand;

import by.training.epam.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class GoToDischargePageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String patientID = request.getParameter("patientID");
        if (patientID != null) {
            request.setAttribute("patientID",patientID);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/dischargePatient.jsp");
        dispatcher.forward(request,response);
//        Writer writer = response.getWriter();
//        writer.write(patientID);
    }
}
