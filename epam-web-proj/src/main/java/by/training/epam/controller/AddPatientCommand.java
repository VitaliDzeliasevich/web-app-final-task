package by.training.epam.controller;

import by.training.epam.controller.util.ParameterName;
import by.training.epam.entity.Patient;
import by.training.epam.service.PatientService;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class AddPatientCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter(ParameterName.NAME);
        String surname = request.getParameter(ParameterName.SURNAME);
        String birthdate = request.getParameter("birthdate");
        String sex = request.getParameter("sex");
        String department = request.getParameter("department");
        int room = Integer.parseInt(request.getParameter("room"));
        String admissionDate = request.getParameter("admissionDate");
        String admissionDiagnosis = request.getParameter("admissionDiagnosis");
        Writer writer = response.getWriter();

        Patient patient = new Patient(name, surname, birthdate, sex, department,room,admissionDate, admissionDiagnosis);

        PatientService service = ServiceFactory.getInstance().getPatientService();
        try {
            service.create(patient);
        } catch (ServiceException e) {
            e.printStackTrace();
            writer.write("Unable to add patient, sorry");
        }


    }
}
