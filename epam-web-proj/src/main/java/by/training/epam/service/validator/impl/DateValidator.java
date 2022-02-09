package by.training.epam.service.validator.impl;

import by.training.epam.service.validator.Validator;
import by.training.epam.service.validator.exception.ValidationException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator implements Validator {

    private DateValidator() {}
    private static final DateValidator instance = new DateValidator();
    public static DateValidator getInstance(){
        return instance;
    }

    @Override
    public boolean validate(Object...attr) throws ValidationException {
        boolean valid = false;
        if (attr.length == 2) {
            String startDate = (String) attr[0];
            String endDate = (String) attr[1];
            if (validateOneDate(startDate) && validateOneDate(endDate)) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date therapyStartDate;
                Date therapyEndDate;
                try {
                    therapyStartDate = formatter.parse(startDate);
                    therapyEndDate = formatter.parse(endDate);
            } catch (ParseException e) {
                throw new ValidationException(e);
            }
                if (therapyStartDate.compareTo(therapyEndDate) <= 0) {
                    valid =true;
                }
            }
        } else {
            valid = validateOneDate((String) attr[0]);
        }
        return valid;
    }

    // checks if input date is not before the actual one
    private boolean validateOneDate(String date) throws ValidationException{
        Date actualDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date therapyEndDate;
        Date formattedActualDate;
        try {
            therapyEndDate = formatter.parse(date);
            formattedActualDate = formatter.parse(formatter.format(actualDate));
        } catch (ParseException e) {
            throw new ValidationException(e);
        }
        return formattedActualDate.compareTo(therapyEndDate) <= 0;
    }

}
