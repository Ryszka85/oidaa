package sample.validator;

import sample.Datamodel.Person;

import java.util.Objects;

public class Validator<T> {
    public static void validatePositiveNumbers(final int number) {
        if (number < 0) {
            final String errorMsg = "ERROR! (Negative numbers not allowed!)";
            throw new IllegalArgumentException(errorMsg);
        }
    }

    public static void validateString(final String stringValue, final String ERROR_MSG) {
        if (stringValue == null || stringValue.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MSG);
        }
    }
    public static void objectNotNull(Object obj) {
        if (Objects.isNull(obj)) {
            final String errorMsg = "ERROR!Illegal Argument. Null not allowed";
            throw new IllegalArgumentException(errorMsg);
        }

    }


}
