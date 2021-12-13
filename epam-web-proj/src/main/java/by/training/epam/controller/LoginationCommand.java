package by.training.epam.controller;

import by.training.epam.controller.exception.ControllerException;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.UserService;
import by.training.epam.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginationCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException,
            ControllerException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        //checking login&password
            UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            if (userService.authorized(login,password)) {
                request.setAttribute("login", login);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp");
                dispatcher.forward(request,response);
            } else {
                request.setAttribute("invalidAuthorization", true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request,response);
            }
        } catch (ServiceException e) {
            throw   new ControllerException(e);
        }
    }


    }

