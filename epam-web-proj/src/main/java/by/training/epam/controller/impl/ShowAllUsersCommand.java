package by.training.epam.controller.impl;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;
import by.training.epam.entity.User;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;
import by.training.epam.service.impl.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowAllUsersCommand implements Command {

    private final Logger log = Logger.getLogger(ShowAllUsersCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserService service = ServiceFactory.getInstance().getUserService();

        List<User> list = null;

        try {
            list = service.getAll();
            list.removeIf(x -> x.getLogin().equals(request.getSession().getAttribute(JSPParameter.LOGIN)));
        } catch (ServiceException e) {
            log.log(Level.ERROR,"Getting ALL USERS ERROR", e);
            String errorMessage = "Smth went wrong, please try later. ;(";
            request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
            response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
        }

        String URL = CommandName.CONTROLLER_COMMAND + CommandName.SHOW_ALL_USERS + "&" + JSPParameter.ARE_USERS_FOUND +
                "=" + true + "&" + JSPParameter.FOUND_USERS + "=" + list;

        request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        request.setAttribute(JSPParameter.ARE_USERS_FOUND, true);
        request.setAttribute(JSPParameter.FOUND_USERS, list);

        request.getRequestDispatcher(JSPPath.MAIN_PAGE_PATH).forward(request,response);
    }
}
