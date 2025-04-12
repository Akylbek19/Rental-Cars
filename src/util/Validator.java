package util;

import java.time.LocalDate;

public class Validator {
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("^\\+?[0-9]{10,15}$");
    }

    public static boolean isValidDateRange(LocalDate start, LocalDate end) {
        return start != null && end != null && !end.isBefore(start);
    }
}