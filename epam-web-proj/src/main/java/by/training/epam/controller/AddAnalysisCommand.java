package by.training.epam.controller;

import by.training.epam.entity.Analysis;
import by.training.epam.service.AnalysisService;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddAnalysisCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int analysisType = Integer.parseInt(request.getParameter("type"));
        int patientId;
        if (request.getParameter("trigger").equals("trigger")) {
           patientId = Integer.parseInt((String)request.getSession().getAttribute("patientID"));
        } else {
            patientId = Integer.parseInt(request.getParameter("patientId"));
        }

        String appointmentDate = request.getParameter("appointmentDate");

        AnalysisService analysisService = ServiceFactory.getInstance().getAnalysisService();
        boolean created = false;

        try {
            created = analysisService.create(new Analysis(patientId, analysisType, appointmentDate));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (created) {
            request.setAttribute("created", "true");
        } else {
            request.setAttribute("created", "false");
        }
        request.getRequestDispatcher("/WEB-INF/jsp/addAnalysis.jsp").forward(request,response);

    }
}
