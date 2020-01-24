package sample.validator;

public class Validator {
    public static void validatePositiveNumbers(final int number) {
        if (number < 0) {
            System.out.println("ERROR! (Negative numbers not allowed!)");
            throw new IllegalArgumentException();
        }
    }

    public static void validateString(final String stringValue, final String ERROR_MSG) {
        if (stringValue == null || stringValue.isEmpty()) {
            System.out.println(ERROR_MSG);
            throw new IllegalArgumentException();
        }
    }


}
