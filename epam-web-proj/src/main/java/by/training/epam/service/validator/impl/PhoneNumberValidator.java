package by.training.epam.service.validator.impl;

import by.training.epam.service.validator.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements Validator {

    private static final String phonePattern = "^\\((17|29|33|44)\\)-[0-9]{3}-[0-9]{2}-[0-9]{2}$";

    private PhoneNumberValidator() {}
    private static final PhoneNumberValidator instance = new PhoneNumberValidator();
    public static PhoneNumberValidator getInstance(){
        return instance;
    }

    @Override
    public boolean validate(Object...attr) {
        String phoneNumber = (String) attr[0];
        Pattern pattern = Pattern.compile(phonePattern);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
