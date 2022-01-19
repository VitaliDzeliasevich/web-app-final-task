package by.training.epam.controller;

import by.training.epam.controller.util.ParameterName;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.UserService;
import by.training.epam.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

public class RegistrationCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean isLoginExist = true;
        String login = request.getParameter(ParameterName.LOGIN);
        String password = request.getParameter(ParameterName.PASSWORD);
        UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            isLoginExist = userService.isLoginExist(login);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (!isLoginExist) {
            HttpSession session = request.getSession();
            session.setAttribute(ParameterName.LOGIN, login);
            session.setAttribute(ParameterName.PASSWORD,password);
            RequestDispatcher dispatcher = request.getRequestDispatcher(ParameterName.REG_DETAILS_PAGE_PATH);
            dispatcher.forward(request,response);
        }
        else {
            Writer writer = response.getWriter();
            writer.write("Such login has already created");
        }
    }
}
