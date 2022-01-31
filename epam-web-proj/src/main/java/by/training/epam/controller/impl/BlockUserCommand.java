package by.training.epam.controller.impl;

import by.training.epam.controller.Command;
import by.training.epam.controller.util.CommandName;
import by.training.epam.controller.util.JSPParameter;
import by.training.epam.controller.util.JSPPath;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;
import by.training.epam.service.impl.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockUserCommand implements Command {

    private final Logger log = Logger.getLogger(BlockUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    int userId = Integer.parseInt(request.getParameter(JSPParameter.USER_ID));

        UserService service = ServiceFactory.getInstance().getUserService();
        boolean blocked = false;
        try {
            blocked = service.blockUser(userId);
        } catch (ServiceException e) {
            log.log(Level.ERROR,"Blocking user error", e);
            String errorMessage = "Blocking user Error, please try later.";
            request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
            response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
        }

        if (blocked) {
            request.setAttribute(JSPParameter.BLOCKED, true);
            String URL = CommandName.CONTROLLER_COMMAND + CommandName.BLOCK_USER + "&" + JSPParameter.BLOCKED + "=" + true;
            request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        } else {
            request.setAttribute(JSPParameter.BLOCKED, JSPParameter.FALSE);
            String URL = CommandName.CONTROLLER_COMMAND + CommandName.BLOCK_USER + "&" + JSPParameter.BLOCKED + "="
                    + JSPParameter.FALSE;
            request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        }
        request.setAttribute(JSPParameter.ARE_USERS_FOUND, true);
        request.setAttribute(JSPParameter.FOUND_USERS, request.getAttribute(JSPParameter.FOUND_USERS));
        request.getRequestDispatcher(JSPPath.MAIN_PAGE_PATH).forward(request,response);
    }
}
