package FitnessApp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

//Validierungen fuer Jtextfields
public class Validation {
    public static boolean isDoubleValid(String doubleValid) {
        try {
            Double.parseDouble(doubleValid);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isIntValid(String intStr) {
        try {
            Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isDateValid(String dateStr) {
        try {
            LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static boolean isTimeValid(String dateStr) {
        try {
            LocalTime.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
