package sample.validator;

public class PersonValidator {
    public static void validateName(final String first, final String last) {
        final String ERROR_MSG = "Error! Invalid Name.. Please enter a valid name";
        Validator.validateString(first, ERROR_MSG);
        Validator.validateString(last, ERROR_MSG);
    }

    public static void validateId(final int id) {
        Validator.validatePositiveNumbers(id);
    }

}
