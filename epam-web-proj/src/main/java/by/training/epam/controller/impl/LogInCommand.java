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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogInCommand implements Command {

    private final Logger log = Logger.getLogger(LogInCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String login = request.getParameter(JSPParameter.LOGIN);
        String password = request.getParameter(JSPParameter.PASSWORD);

        UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            if (userService.authorized(login,password) && userService.isNotBlocked(login)) {
                HttpSession session = request.getSession();
                session.setAttribute(JSPParameter.LOGIN, login);
                String role = userService.getRole(login);
                session.setAttribute(JSPParameter.ROLE, role);
                if (role.equals(JSPParameter.NURSE)) {
                    int departmentId = userService.getByLogin(login).getDepartmentsId();
                    session.setAttribute(JSPParameter.DEPARTMENT_ID, departmentId);
                }
                String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_MAIN_PAGE;
                session.setAttribute(JSPParameter.LAST_REQUEST, URL);
                response.sendRedirect(URL);
            } else if (!userService.isNotBlocked(login)) {
                String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_INITIAL_PAGE  + "&" +
                        JSPParameter.BLOCKED + "=" + true;
                request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
                request.setAttribute(JSPParameter.BLOCKED, true);
                request.getRequestDispatcher(JSPPath.INITIAL_PAGE_PATH).forward(request,response);
            }
            else {
                String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_INITIAL_PAGE + "&" +
                        JSPParameter.INVALID_AUTHORIZATION + "=" + true;
                request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
                request.setAttribute(JSPParameter.INVALID_AUTHORIZATION, true);
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

