package by.training.epam.controller;

import by.training.epam.controller.util.ParameterName;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguageCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String local = request.getParameter(ParameterName.LOCALIZATION);
        String address = request.getParameter("address");

        request.getSession().setAttribute(ParameterName.LOCALIZATION, local);

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request,response);
    }
}
