package by.training.epam.controller.impl.gotocommand;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToUpdateAnalysisCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String analysisId = request.getParameter(JSPParameter.ANALYSIS_ID);
        request.setAttribute(JSPParameter.ANALYSIS_ID,analysisId);

        request.getRequestDispatcher(JSPPath.UPDATE_ANALYSIS_PATH).forward(request,response);


    }
}
