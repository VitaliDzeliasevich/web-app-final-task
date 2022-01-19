package by.training.epam.controller;

import by.training.epam.controller.util.ParameterName;
import by.training.epam.entity.User;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.UserService;
import by.training.epam.service.exception.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

public class RegistrationDetailsCommand implements Command{

    Logger log = Logger.getLogger(RegistrationDetailsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(ParameterName.LOGIN);
        String password = (String) session.getAttribute(ParameterName.PASSWORD);
        String name = request.getParameter(ParameterName.NAME);
        String surname = request.getParameter(ParameterName.SURNAME);
        int position = Integer.parseInt(request.getParameter("position"));
        String specialisation = request.getParameter("specialisation");
        String phone = request.getParameter("phone");

        Writer writer = response.getWriter();

        UserService userService = ServiceFactory.getInstance().getUserService();
        boolean created=false;
        try {
            created = userService.create(new User(login, password, name, surname, position));
        } catch (ServiceException e) {
            log.log(Level.ERROR,e);
            writer.write("Smth went wrong. Try later");
        }

        if (created) {
            session.setAttribute(ParameterName.ROLE,position);
            RequestDispatcher dispatcher = request.getRequestDispatcher(ParameterName.MAIN_PAGE_PATH);
            dispatcher.forward(request,response);
        }
        else {
            writer.write("Smth went wrong. Try later");
        }
    }
}
