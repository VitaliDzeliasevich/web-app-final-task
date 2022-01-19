package by.training.epam.controller;

import by.training.epam.controller.util.ParameterName;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.UserService;
import by.training.epam.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class LoginationCommand implements Command{

    Logger log = Logger.getLogger(LoginationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String login = request.getParameter(ParameterName.LOGIN);
        String password = request.getParameter(ParameterName.PASSWORD);
        Writer writer = response.getWriter();

        //checking login&password
            UserService userService = ServiceFactory.getInstance().getUserService();

        try {
            if (userService.authorized(login,password)) {
                request.getSession().setAttribute(ParameterName.LOGIN, login);
                request.getSession().setAttribute(ParameterName.ROLE, userService.getRole(login));
                request.getSession().removeAttribute(ParameterName.INVALID_AUTHORIZATION);
                request.getRequestDispatcher(ParameterName.MAIN_PAGE_PATH).forward(request,response);
            } else {
                if (request.getSession().getAttribute(ParameterName.INVALID_AUTHORIZATION) == null) {
                    request.getSession().setAttribute(ParameterName.INVALID_AUTHORIZATION, true);
                }
                response.sendRedirect(ParameterName.INDEX_PAGE_PATH);
            }
        } catch (ServiceException e) {
            log.log(Level.ERROR,e);
            writer.write("Smth went wrong, please try later");
        }
    }


    }

