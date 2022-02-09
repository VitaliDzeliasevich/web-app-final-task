package by.training.epam.controller.impl;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChooseDrugGroupCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int drugGroupId = Integer.parseInt(request.getParameter(JSPParameter.GROUP));


        String URL = CommandName.CONTROLLER_COMMAND + CommandName.CHOOSE_DRUG_GROUP + "&" +JSPParameter.GROUP + "=" +
                drugGroupId;
        request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        request.setAttribute(JSPParameter.GROUP, drugGroupId);
        request.getRequestDispatcher(JSPPath.ADD_TREATMENT_PAGE_PATH).forward(request,response);
    }
}
