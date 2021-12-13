package by.training.epam.controller;

import by.training.epam.controller.exception.ControllerException;
import by.training.epam.entity.User;
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

public class DetailsRegistrationCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException,
            ControllerException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        String password = (String) session.getAttribute("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        int position = Integer.parseInt(request.getParameter("position"));
        String specialisation = request.getParameter("specialisation");
        String phone = request.getParameter("phone");


        UserService userService = ServiceFactory.getInstance().getUserService();
        boolean created = false;
        try {
            created = userService.create(new User(login, password, name, surname, position));
        } catch (ServiceException e) {
            throw  new ControllerException(e);
        }
        Writer writer = response.getWriter();
        if (created) {
            session.setAttribute("role",position);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp");
            dispatcher.forward(request,response);
        }
        else {
            writer.write("Smth went wrong. Try later");
        }
    }
}
