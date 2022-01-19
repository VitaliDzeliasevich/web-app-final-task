package by.training.epam.controller.gotocommand;

import by.training.epam.controller.Command;
import by.training.epam.entity.Patient;
import by.training.epam.service.PatientService;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class GoToPatientPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int patientId = Integer.parseInt(request.getParameter("patientID"));

        PatientService patientService = ServiceFactory.getInstance().getPatientService();
        Patient patient = null;
        try {
            patient = patientService.getEntityById(patientId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.setAttribute("patient", patient);
        request.getRequestDispatcher("/WEB-INF/jsp/patientPage.jsp").forward(request,response);


//        Writer writer = response.getWriter();
//        writer.write("Wassup patient  " + patientId);
//        writer.close();

    }
}
