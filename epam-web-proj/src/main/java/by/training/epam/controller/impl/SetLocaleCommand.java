package by.training.epam.controller.impl;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class SetLocaleCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String local = request.getParameter(JSPParameter.LOCALE);
        request.getSession().setAttribute(JSPParameter.LOCALE, local);


        response.sendRedirect((String) request.getSession().getAttribute(JSPParameter.LAST_REQUEST));
    }
}
