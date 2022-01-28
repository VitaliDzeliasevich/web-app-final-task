package by.training.epam.controller.impl;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.impl.UserService;
import by.training.epam.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogInCommand implements Command {

    private final Logger log = Logger.getLogger(LogInCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String login = request.getParameter(JSPParameter.LOGIN);
        String password = request.getParameter(JSPParameter.PASSWORD);

        UserService userService = ServiceFactory.getInstance().getUserService();

        try {
            if (userService.authorized(login,password)) {
                request.getSession().setAttribute(JSPParameter.LOGIN, login);
                request.getSession().setAttribute(JSPParameter.ROLE, userService.getRole(login));
                request.getSession().removeAttribute(JSPParameter.INVALID_AUTHORIZATION);

                String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_MAIN_PAGE;
                request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
                response.sendRedirect(URL);
            } else {
                if (request.getSession().getAttribute(JSPParameter.INVALID_AUTHORIZATION) == null) {
                    request.getSession().setAttribute(JSPParameter.INVALID_AUTHORIZATION, true);
                }
                String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_INITIAL_PAGE;
                request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
                request.getRequestDispatcher(JSPPath.INITIAL_PAGE_PATH).forward(request,response);
            }
        } catch (ServiceException e) {
            log.log(Level.ERROR,"Logination error", e);
            String errorMessage = "LogIn Error, please try later.";
            request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
            response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
        }
    }


    }

