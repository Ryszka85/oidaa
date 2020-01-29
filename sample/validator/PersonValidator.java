package sample.validator;

import javafx.scene.control.DatePicker;
import sample.Datamodel.Person;

import java.util.Date;

public class PersonValidator {
    public static void validateName(final String first, final String last) {
        final String ERROR_MSG = "Error! Invalid Name.. Please enter a valid name";
        Validator.validateString(first, ERROR_MSG);
        Validator.validateString(last, ERROR_MSG);
    }

    public static void validateDate(DatePicker birth_date) {
        if (birth_date.getValue() == null) {
            final String errorMsg = "ERROR!Birth date is missing.";
            throw new IllegalArgumentException(errorMsg);
        }
    }

    public static void validatePersonAttributes(Person customer) {
        if (customer.getFirstName() == null ||
                customer.getLastName() == null ||
                customer.getBirthDate() == null
        )
            throw new IllegalArgumentException("ERROR!Person could not be created.Illegal Argument.");
    }

    public static void validatePerson(Person person) {
        if (person == null) {
            final String errorMsg = "ERROR!Person could not been created.";
            throw new NullPointerException(errorMsg);
        }
    }

    public static void validateId(final int id) {
        Validator.validatePositiveNumbers(id);
    }

}
