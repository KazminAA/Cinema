package helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by lex on 23.09.16.
 */
public class DateTimeHelpers {
    public static String timeFromLDT(LocalDateTime ldt) {
        return ldt.format(DateTimeFormatter.ofPattern("H':'MM"));
    }
}
