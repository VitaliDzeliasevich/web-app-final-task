package by.training.epam.controller;

import by.training.epam.controller.exception.ControllerException;
import by.training.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final CommandProvider provider = new CommandProvider();

    public Controller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            execute(req, resp);
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            execute(req, resp);
        } catch (ControllerException e) {
            e.printStackTrace();
        }

    }

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException,
            ControllerException {
        Command command = provider.getCommand(req.getParameter("command"));
        command.execute(req,resp);
    }
}
