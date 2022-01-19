package by.training.epam.controller;

import by.training.epam.entity.DiseaseHistory;
import by.training.epam.service.PatientService;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.WriteAbortedException;
import java.io.Writer;

public class DischargePatientCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int diseaseHistoryId = Integer.parseInt(request.getParameter("diseaseHistoryId"));
        String epicrysis = request.getParameter("epicrysis");
        String dischargeDate = request.getParameter("dischargeDate");

        DiseaseHistory diseaseHistory = new DiseaseHistory();
        diseaseHistory.setDischargingDate(dischargeDate);
        diseaseHistory.setEpicrysis(epicrysis);
        diseaseHistory.setId(diseaseHistoryId);

        PatientService service = ServiceFactory.getInstance().getPatientService();
        Writer writer = response.getWriter();
        boolean discharged = false;
        try {
            discharged = service.discharge(diseaseHistory);
        } catch (ServiceException e) {
            writer.write("Updating error, try later");
            e.printStackTrace();
        }

        if (discharged) {
            request.setAttribute("discharged", "true");
            request.getRequestDispatcher("/WEB-INF/jsp/dischargePatient.jsp").forward(request,response);
        } else {
            request.setAttribute("discharged", "false");
            request.getRequestDispatcher("/WEB-INF/jsp/dischargePatient.jsp").forward(request,response);
        }
    }
}
