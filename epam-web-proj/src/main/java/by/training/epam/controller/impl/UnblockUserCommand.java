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

public class UnblockUserCommand implements Command {

    private final Logger log = Logger.getLogger(UnblockUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int userId = Integer.parseInt(request.getParameter(JSPParameter.USER_ID));
        List<User> list = null;

        UserService service = ServiceFactory.getInstance().getUserService();
        try {
            service.unblockUser(userId);
            list = service.getAll();
            list.removeIf(x -> x.getLogin().equals(request.getSession().getAttribute(JSPParameter.LOGIN)));
        } catch (ServiceException e) {
            log.log(Level.ERROR,"UnBlocking user error", e);
            String errorMessage = "UnBlocking user Error, please try later.";
            request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
            response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
        }

        request.setAttribute(JSPParameter.ARE_USERS_FOUND, true);
        request.setAttribute(JSPParameter.FOUND_USERS, list);


        request.getRequestDispatcher(JSPPath.MAIN_PAGE_PATH).forward(request,response);
    }
}
