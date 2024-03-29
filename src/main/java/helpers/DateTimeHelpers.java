package helpers;

import dto.SessionDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Created by lex on 23.09.16.
 */
public class DateTimeHelpers {
    public static String timeFromLDT(LocalDateTime ldt) {
        return ldt.format(DateTimeFormatter.ofPattern("H':'MM"));
    }

    public static LocalTime getTimeObj(LocalDateTime ldt) {
        return ldt.toLocalTime();
    }

    public static String dateFormLDT(LocalDateTime ldt) {
        return ldt.format(DateTimeFormatter.ofPattern("dd'.'MM'.'YY")) + " "
                + ldt.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("ru"));
    }

    public static LocalDate getDate(LocalDateTime ldt) {
        return ldt.toLocalDate();
    }

    public static String dateFormLDT(LocalDate ldt) {
        return ldt.format(DateTimeFormatter.ofPattern("dd'.'MM'.'YY")) + " "
                + ldt.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("ru"));
    }

    public static boolean isSessionTime(LocalDate date, SessionDTO sessionDTO) {
        return sessionDTO.getDateOfSeance().toLocalDate().equals(date);
    }

    public static LocalTime getSeanceEnd(LocalDateTime begin, int duration) {
        return begin.plusMinutes(duration).toLocalTime();
    }
}
