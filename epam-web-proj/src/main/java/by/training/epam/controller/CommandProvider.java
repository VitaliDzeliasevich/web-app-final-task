package by.training.epam.controller;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private final Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put("logination", new LoginationCommand());
        commands.put("GO_TO_REGISTRATION", new GoToRegistrationCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("detailsRegistration", new DetailsRegistrationCommand());
    }

    public Command getCommand(String key) {
        return commands.get(key);
    }
}
