package by.training.epam.service.validator.impl;

import by.training.epam.service.validator.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationValidator implements Validator {

    private static final String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$";
    private static final String phonePattern = "^\\((17|29|33|44)\\)-[0-9]{3}-[0-9]{2}-[0-9]{2}$";

    private RegistrationValidator() {}

    private static final RegistrationValidator instance = new RegistrationValidator();

    public static RegistrationValidator getInstance(){
        return instance;
    }

    @Override
    public boolean validatePasswordAndLogin(String password, String login) {
        boolean validated = false;
        if (!login.isEmpty() && !password.isEmpty()) {
            validated = validatePassword(password);
        }
        return validated;
    }

    @Override
    public boolean validatePhoneNumber(String phone) {
        Pattern pattern = Pattern.compile(phonePattern);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }


    private boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }


}
