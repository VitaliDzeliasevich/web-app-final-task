package by.training.epam.service.validator;

import by.training.epam.service.validator.exception.ValidationException;



public interface Validator {

   boolean validate(Object...attr) throws ValidationException;
}
