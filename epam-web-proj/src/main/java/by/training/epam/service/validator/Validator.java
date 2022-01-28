package by.training.epam.service.validator;

public interface Validator {

    boolean validatePasswordAndLogin(String password, String login);

    boolean validatePhoneNumber(String phone);
}
