package by.training.epam.controller.impl;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;
import by.training.epam.entity.Analysis;
import by.training.epam.entity.transfer.PatientAnalysis;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;
import by.training.epam.service.impl.AnalysisService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowDepartmentAnalysisCommand implements Command {

    private final Logger log = Logger.getLogger(ShowDepartmentAnalysisCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int departmentId = (int) request.getSession().getAttribute(JSPParameter.DEPARTMENT_ID);
        String date = request.getParameter(JSPParameter.DATE);

        List<PatientAnalysis> list;

        AnalysisService service = ServiceFactory.getInstance().getAnalysisService();
        try {
            list = service.getByDepartmentAndDate(departmentId,date);
            request.setAttribute(JSPParameter.FOUND_ANALYSIS, list);
            request.getRequestDispatcher(JSPPath.MAIN_PAGE_PATH).forward(request,response);
        } catch (ServiceException e) {
            log.log(Level.ERROR,"Getting analysis error", e);
            String errorMessage = "Smth went wrong, please try later.";
            request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
            response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
        }
    }
}
