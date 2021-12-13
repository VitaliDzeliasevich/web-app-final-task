package by.training.epam.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean isLoginExist = false;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        //checking if there such a login exist, password validating
        if (!isLoginExist) {
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            session.setAttribute("password",password);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/regDetails.jsp");
            dispatcher.forward(request,response);
        }
    }
}
