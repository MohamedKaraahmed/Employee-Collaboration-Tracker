package com.example.employeecollaborationtracker.service.Impl;

import com.example.employeecollaborationtracker.service.DateValidator;
import com.example.employeecollaborationtracker.util.constants.ConstantValues;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static com.example.employeecollaborationtracker.util.common.ExceptionMessages.DATE_PARSE_FAIL;

@Component
public class DateValidatorImpl implements DateValidator {

    @Override
    public Date validateDate(String dateAsString) {
        for (String format : ConstantValues.getDateFormats()) {
            try {
                //try to find suitable format for date
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                return dateFormat.parse(dateAsString);
            } catch (DateTimeParseException | ParseException ignored) {
                //ignore current format
            }
        }

        throw new DateTimeParseException(String.format(DATE_PARSE_FAIL, dateAsString), dateAsString, 0);
    }

}

