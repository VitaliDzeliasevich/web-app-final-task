package by.training.epam.controller.impl;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPPath;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.impl.UserService;
import by.training.epam.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {

    private final Logger log = Logger.getLogger(RegistrationCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter(JSPParameter.LOGIN);
        String password = request.getParameter(JSPParameter.PASSWORD);
        UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            if (userService.validateLoginAndPassword(login, password)) {
                boolean isLoginExist = userService.isLoginExist(login);
                if (!isLoginExist) {
                    request.setAttribute(JSPParameter.LOGIN, login);
                    request.setAttribute(JSPParameter.PASSWORD, password);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.REG_DETAILS_PAGE_PATH);
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute(JSPParameter.LOGIN_EXISTS, true);
                    request.setAttribute(JSPParameter.LOGIN, login);
                    request.getRequestDispatcher(JSPPath.REGISTRATION_PAGE_PATH).forward(request, response);
                }
            } else {
                request.setAttribute(JSPParameter.INCORRECT_PASSWORD, true);
                request.getRequestDispatcher(JSPPath.REGISTRATION_PAGE_PATH).forward(request, response);
            }
        }
            catch(ServiceException e){
                log.log(Level.ERROR, "Registration error", e);
                String errorMessage = "Smth went wrong, please try later.";
                request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
                response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
            }

        }
    }


