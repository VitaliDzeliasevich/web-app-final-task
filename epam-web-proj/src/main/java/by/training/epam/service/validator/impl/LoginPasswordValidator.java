package by.training.epam.service.validator.impl;

import by.training.epam.service.validator.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPasswordValidator implements Validator {

    private static final String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$";

    private LoginPasswordValidator() {}
    private static final LoginPasswordValidator instance = new LoginPasswordValidator();
    public static LoginPasswordValidator getInstance(){
        return instance;
    }


    @Override
    public boolean validate(Object...attr) {
        String login = (String) attr[0];
        String password = (String) attr[1];
        boolean validated = false;
        if (!login.isEmpty() && !password.isEmpty()) {
            validated = validatePassword(password);
        }
        return validated;
    }

    private boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
