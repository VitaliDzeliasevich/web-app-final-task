package by.training.epam.controller.impl;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.removeAttribute(JSPParameter.LOGIN);
        session.removeAttribute(JSPParameter.ROLE);
        session.invalidate();
        String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_INITIAL_PAGE;
        request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        response.sendRedirect(URL);
    }
}
