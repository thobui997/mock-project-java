package vn.manage.system.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    public static final String TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    /**
     * convert {@link LocalDateTime} into string with time format yyyy/MM/dd HH:mm:ss
     * 
     * @param localDateTime
     * @return string with time format yyyy/MM/dd HH:mm:ss
     */
    public static String convertLocalDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(FORMATTER);
    }

}
