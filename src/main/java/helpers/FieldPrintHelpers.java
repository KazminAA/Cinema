package helpers;

/**
 * Created by Alexandr on 26.09.2016.
 */
public class FieldPrintHelpers {
    public static String durationToH(int durationMin) {
        return (durationMin / 60) + "h. " + (durationMin - (durationMin / 60) * 60) + "min.";
    }

    public static boolean stringEq(String value, String key) {
        return value.equals(key);
    }
}
