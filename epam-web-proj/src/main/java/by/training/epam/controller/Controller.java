package by.training.epam.controller;

import by.training.epam.controller.util.ParameterName;

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
            execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            execute(req, resp);
    }

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Command command = provider.getCommand(req.getParameter(ParameterName.COMMAND));
        command.execute(req,resp);
    }
}
