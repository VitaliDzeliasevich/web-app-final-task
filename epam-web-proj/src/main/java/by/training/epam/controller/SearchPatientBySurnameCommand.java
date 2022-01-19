package by.training.epam.controller;
import by.training.epam.controller.util.ParameterName;
import by.training.epam.entity.Patient;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class SearchPatientBySurnameCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String patientSurname = request.getParameter(ParameterName.SEARCH_PATIENT);
        Writer writer = response.getWriter();

        List<Patient> patients = null;

        try {
            patients = ServiceFactory.getInstance().getPatientService().getBySurname(patientSurname);
        } catch (ServiceException e) {
            writer.write("Sorry, smth went wrong");
        }

        if (patients!=null) {
            request.setAttribute(ParameterName.FOUND, true);
            request.setAttribute(ParameterName.SEARCHED_PATIENT,patients);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(ParameterName.MAIN_PAGE_PATH);
        dispatcher.forward(request,response);
    }
}
