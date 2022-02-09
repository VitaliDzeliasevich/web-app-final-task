package by.training.epam.controller.impl.gotocommand;

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

public class GoToPersonalInfoCommand implements Command {

    private final Logger log = Logger.getLogger(GoToPersonalInfoCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login;
        if (request.getParameter(JSPParameter.LOGIN)==null) {
            login = (String) request.getSession().getAttribute(JSPParameter.LOGIN);
            String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_PERSONAL_INFO;
            request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        } else {
            login=request.getParameter(JSPParameter.LOGIN);
            String URL = CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_PERSONAL_INFO + "&" + JSPParameter.LOGIN
                    + "=" +login;
            request.getSession().setAttribute(JSPParameter.LAST_REQUEST, URL);
        }

        UserService service = ServiceFactory.getInstance().getUserService();
        User user = null;
        try {
            user = service.getByLogin(login);
        } catch (ServiceException e) {
            log.log(Level.ERROR,"USER getting by LOGIN error", e);
            String errorMessage = "Smth went wrong, please try later :(";
            request.getSession().setAttribute(JSPParameter.ERROR_MESSAGE, errorMessage);
            response.sendRedirect(CommandName.CONTROLLER_COMMAND + CommandName.GO_TO_ERROR_PAGE);
        }
        request.getSession().setAttribute(JSPParameter.USER, user);
        request.getRequestDispatcher(JSPPath.PERSONAL_INFO_PATH).forward(request,response);
    }
}
