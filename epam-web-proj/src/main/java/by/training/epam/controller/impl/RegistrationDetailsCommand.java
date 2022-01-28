package by.training.epam.controller.impl;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPPath;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.entity.User;
import by.training.epam.service.Service;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.impl.UserService;
import by.training.epam.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationDetailsCommand implements Command {

    private final Logger log = Logger.getLogger(RegistrationDetailsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(JSPParameter.LOGIN);
        String password = (String) session.getAttribute(JSPParameter.PASSWORD);
        String name = request.getParameter(JSPParameter.NAME);
        String surname = request.getParameter(JSPParameter.SURNAME);
        int role = Integer.parseInt(request.getParameter(JSPParameter.POSITION));
        String phone = request.getParameter(JSPParameter.PHONE);

        UserService userService = ServiceFactory.getInstance().getUserService();

        if (!userService.validatePhone(phone)) {
            request.setAttribute(JSPParameter.INCORRECT_PHONE, true);
            request.getRequestDispatcher(JSPPath.REG_DETAILS_PAGE_PATH).forward(request, response);
        } else {
            boolean created = false;
            try {
                created = userService.create(new User(login, password, name, surname, role, phone));
            } catch (ServiceException e) {
                log.log(Level.ERROR,"Registration error", e);
                String errorMessage = "Smth went wrong, please try later.";
                request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
                response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
            }

            if (created) {
                session.setAttribute(JSPParameter.ROLE, role);
                RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPath.MAIN_PAGE_PATH);
                dispatcher.forward(request, response);
            } else {
                String errorMessage = "Smth went wrong, please try later.";
                request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
                response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
            }
        }
    }
}
